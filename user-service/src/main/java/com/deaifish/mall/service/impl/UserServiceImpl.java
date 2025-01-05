package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.SetPasswordDTO;
import com.deaifish.mall.pojo.dto.SetPaymentDTO;
import com.deaifish.mall.pojo.dto.UserDTO;
import com.deaifish.mall.pojo.po.QRolePO;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.pojo.po.UserPO;
import com.deaifish.mall.pojo.vo.UserBriefVO;
import com.deaifish.mall.pojo.vo.UserDetailedVO;
import com.deaifish.mall.repository.UserRepository;
import com.deaifish.mall.service.UserService;
import com.deaifish.mall.util.EncryptUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:23
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private UserRepository userRepository;
    @Resource
    private BIZServiceApi bizServiceApi;
    @Resource
    private PathProperties pathProperties;

    private final static QUserPO USER_PO = QUserPO.userPO;
    private final static QRolePO ROLE_PO = QRolePO.rolePO;


    @Override
    public List<UserBriefVO> getAll() {
        List<UserPO> userPOS = jpaQueryFactory.select(USER_PO).from(USER_PO).fetch();
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
    public void setPassword(SetPasswordDTO passwordDTO) {
        String password = EncryptUtil.encode(passwordDTO.getPassword());
        if (password == null) {
            throw new MallException("密码格式有误，请重新输入");
        }
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.password, password)
                .where(USER_PO.userId.eq(passwordDTO.getUserId())).execute();
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

        UserPO user = jpaQueryFactory.select(USER_PO).from(USER_PO).where(USER_PO.wxId.eq(userDTO.getWxId())).fetchOne();
        if (user == null) {
            throw new MallException("用户注册失败，请稍后再试");
        }

        return getUserDetailedVO(user);
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
                .set(USER_PO.avatar, userDTO.getAvatar())
                .set(USER_PO.phone, userDTO.getPhone())
                .set(USER_PO.message, userDTO.getMessage())
                .set(USER_PO.email, userDTO.getEmail())
                .set(USER_PO.status, userDTO.getStatus())
                .set(USER_PO.integral, userDTO.getIntegral())
                .set(USER_PO.roleId, userDTO.getRoleId())
                .where(USER_PO.userId.eq(userDTO.getUserId())).execute();

        UserPO po = jpaQueryFactory.selectFrom(USER_PO).where(USER_PO.userId.eq(userDTO.getUserId())).fetchOne();

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
}
