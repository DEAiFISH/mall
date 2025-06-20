package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ResetPasswordDTO;
import com.deaifish.mall.pojo.dto.SetPaymentDTO;
import com.deaifish.mall.pojo.dto.UserDTO;
import com.deaifish.mall.pojo.po.QRolePO;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.pojo.po.UserPO;
import com.deaifish.mall.pojo.qo.UserQO;
import com.deaifish.mall.pojo.vo.UserBriefVO;
import com.deaifish.mall.pojo.vo.UserDetailedVO;
import com.deaifish.mall.repository.UserRepository;
import com.deaifish.mall.service.UserService;
import com.deaifish.mall.util.EncryptUtil;
import com.deaifish.mall.util.JWTUtil;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final BIZServiceApi bizServiceApi;
    private final PathProperties pathProperties;
    private final JWTUtil jwtUtil;

    private final static QUserPO USER_PO = QUserPO.userPO;
    private final static QRolePO ROLE_PO = QRolePO.rolePO;


    @Override
    public List<UserBriefVO> getAll(UserQO qo) {

        List<UserPO> userPOS = jpaQueryFactory.select(USER_PO)
                .where(createParam(qo)).from(USER_PO)
                .where().fetch();
        ArrayList<UserBriefVO> list = new ArrayList<>();
        userPOS.forEach(userPO -> {
            UserBriefVO bean = BeanUtil.toBean(userPO, UserBriefVO.class);
            Byte roleId = userPO.getRoleId();
            String roleName = jpaQueryFactory.select(ROLE_PO.name).from(ROLE_PO).where(ROLE_PO.roleId.eq(roleId)).fetchOne();
            bean.setRoleName(roleName);
            list.add(bean);
        });

        return list;
    }

    @Override
    public UserDetailedVO getById(Long id) {
        UserPO userPo = jpaQueryFactory.select(USER_PO).from(USER_PO).where(USER_PO.userId.eq(id)).fetchOne();
        if (userPo == null) {
            throw new MallException("用户不存在");
        }

        return getUserDetailedVO(userPo);
    }

    @Override
    @Transactional
    public void setAvatar(Long id, String avatar) {
        UserPO userPo = jpaQueryFactory.select(USER_PO).from(USER_PO).where(USER_PO.userId.eq(id)).fetchOne();
        if (userPo == null) {
            throw new MallException("用户不存在");
        }
        bizServiceApi.delete(userPo.getAvatar());
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.avatar, avatar)
                .where(USER_PO.userId.eq(id)).execute();
    }

    @Override
    @Transactional
    public void setPassword(ResetPasswordDTO resetPasswordDTO) {
        UserPO po = jpaQueryFactory.selectFrom(USER_PO).where(USER_PO.userId.eq(resetPasswordDTO.getUserId())).fetchOne();
        if (po == null) {
            throw new MallException("用户不存在");
        }
        EncryptUtil.matches(resetPasswordDTO.getOldPassword(), po.getPassword());
        String password = EncryptUtil.encode(resetPasswordDTO.getPassword());
        if (password == null) {
            throw new MallException("密码格式有误，请重新输入");
        }
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.password, password)
                .where(USER_PO.userId.eq(resetPasswordDTO.getUserId())).execute();
    }

    @Override
    @Transactional
    public void setPaymentPassword(SetPaymentDTO paymentDTO) {
        String dbPassword = jpaQueryFactory.select(USER_PO.password)
                .from(USER_PO).where(USER_PO.userId.eq(paymentDTO.getUserId())).fetchOne();
        if (!EncryptUtil.matches(paymentDTO.getPassword(), dbPassword)) {
            throw new MallException("登录密码错误");
        }
        String paymentPassword = EncryptUtil.encode(paymentDTO.getPaymentPassword());
        if (paymentPassword == null) {
            throw new MallException("支付密码格式有误，请重新输入");
        }
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.paymentPassword, paymentPassword)
                .where(USER_PO.userId.eq(paymentDTO.getUserId())).execute();
    }

    @Override
    @Transactional
    public UserDetailedVO signUp(UserDTO userDTO) {
        UserPO po = BeanUtil.toBean(userDTO, UserPO.class);
        po.setLastLogin(new Date());
        po.setPassword(EncryptUtil.encode(po.getPassword()));
        po.setPaymentPassword(EncryptUtil.encode(po.getPaymentPassword()));
        userRepository.save(po);
        entityManager.refresh(po);

        return getUserDetailedVO(po);
    }

    @Override
    public Boolean existsById(String wxId) {
        return userRepository.existsByWxId(wxId);
    }

    @Override
    @Transactional
    public UserDetailedVO update(UserDTO userDTO) {
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.nickName, userDTO.getNickName())
                .set(USER_PO.realName, userDTO.getRealName())
                .set(USER_PO.gender, userDTO.getGender())
                .set(USER_PO.birthday, userDTO.getBirthday())
                .set(USER_PO.phone, userDTO.getPhone())
                .set(USER_PO.message, userDTO.getMessage())
                .set(USER_PO.email, userDTO.getEmail())
                .set(USER_PO.status, userDTO.getStatus())
                .where(USER_PO.userId.eq(userDTO.getUserId())).execute();

        UserPO po = jpaQueryFactory.selectFrom(USER_PO).where(USER_PO.userId.eq(userDTO.getUserId())).fetchOne();

        if (po.getStatus() == 0) {
            jwtUtil.deleteJwtFromRedis(po.getWxId());
        }

        return getUserDetailedVO(po);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserPO po = jpaQueryFactory.selectFrom(USER_PO).where(USER_PO.userId.eq(id)).fetchOne();
        if (po == null) {
            throw new MallException("用户不存在");
        }

        String avatar = po.getAvatar();
        // 如果不是默认头像，则删除
        if (!avatar.endsWith(pathProperties.getUserDefaultPhotoName())) {
            bizServiceApi.delete(avatar);
        }

        jpaQueryFactory.delete(USER_PO).where(USER_PO.userId.eq(id)).execute();
    }

    private UserDetailedVO getUserDetailedVO(UserPO po) {
        UserDetailedVO vo = BeanUtil.toBean(po, UserDetailedVO.class);
        Byte roleId = po.getRoleId();
        String roleName = jpaQueryFactory.select(ROLE_PO.name).from(ROLE_PO).where(ROLE_PO.roleId.eq(roleId)).fetchOne();
        vo.setRoleName(roleName);

        return vo;
    }

    private static Predicate[] createParam(UserQO qo) {
        List<Predicate> param = new ArrayList<>();
        if (StrUtil.isNotBlank(qo.getWxId())) {
            param.add(USER_PO.wxId.like("%" + qo.getWxId() + "%"));
        }
        if (StrUtil.isNotBlank(qo.getNickName())) {
            param.add(USER_PO.nickName.like("%" + qo.getNickName() + "%"));
        }
        if (StrUtil.isNotBlank(qo.getEmail())) {
            param.add(USER_PO.email.like("%" + qo.getEmail() + "%"));
        }
        if (StrUtil.isNotBlank(qo.getPhone())) {
            param.add(USER_PO.phone.like("%" + qo.getPhone() + "%"));
        }
        if(qo.getCreateTimeFrom() != null && qo.getCreateTimeTo() != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(qo.getCreateTimeTo());
            // 把日期往后增加一天,整数  往后推,负数往前移动
            calendar.add(Calendar.DATE, 1);
            // 这个时间就是日期往后推一天的结果
            Date date = calendar.getTime();

            param.add(USER_PO.createTime.between(qo.getCreateTimeFrom(),date));
        }
        return param.toArray(new Predicate[0]);
    }
}
