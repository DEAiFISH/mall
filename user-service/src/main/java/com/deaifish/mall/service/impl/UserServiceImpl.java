package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.entity.dto.SetPasswordDTO;
import com.deaifish.mall.entity.dto.SetPaymentDTO;
import com.deaifish.mall.entity.dto.UserDTO;
import com.deaifish.mall.entity.po.QUserPO;
import com.deaifish.mall.entity.po.UserPO;
import com.deaifish.mall.entity.vo.UserBriefVO;
import com.deaifish.mall.entity.vo.UserDetailedVO;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.repository.UserRepository;
import com.deaifish.mall.service.UserService;
import com.deaifish.mall.util.EncryptUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final static QUserPO USER_PO = QUserPO.userPO;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserBriefVO> getAll() {
        List<UserPO> userPOS = jpaQueryFactory.select(USER_PO).from(USER_PO).fetch();
        ArrayList<UserBriefVO> list = new ArrayList<>();
        userPOS.forEach(userPO -> {
            UserBriefVO bean = BeanUtil.toBean(userPO, UserBriefVO.class);
            list.add(bean);
            log.info("{}", bean);
        });

        return list;
    }

    @Override
    public UserDetailedVO getById(Long id) {
        UserPO userPO = jpaQueryFactory.select(USER_PO).from(USER_PO).where(USER_PO.userId.eq(id)).fetchOne();
        if (userPO == null) {
            throw new MallException("用户不存在");
        }

        return BeanUtil.toBean(userPO, UserDetailedVO.class);
    }

    @Override
    @Transactional
    public void setPassword(SetPasswordDTO passwordDTO) {
        String password = EncryptUtil.encode(passwordDTO.getPassword());
        if(password == null){
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
        if(!EncryptUtil.matches(paymentDTO.getPassword(), dbPassword)){
            throw new MallException("原密码错误");
        }
        String paymentPassword = EncryptUtil.encode(paymentDTO.getPaymentPassword());
        if(paymentPassword == null){
            throw new MallException("支付密码格式有误，请重新输入");
        }
        jpaQueryFactory.update(USER_PO)
                .set(USER_PO.paymentPassword, paymentPassword)
                .where(USER_PO.userId.eq(paymentDTO.getUserId())).execute();
    }

    @Override
    @Transactional
    public void signUp(UserDTO userDTO) {
        jpaQueryFactory.insert(USER_PO).execute();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        jpaQueryFactory.update(USER_PO)
                .where(USER_PO.userId.eq(userDTO.getUserId()))
                .execute();
    }
}
