package com.deaifish.mall.listener;

import com.deaifish.mall.config.JPAFactoryConfig;
import com.deaifish.mall.entity.po.QUserPO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description  登陆成功监听
 *
 * @author DEAiFISH
 * @date 2024/12/14 19:23
 */
@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Resource
    private JPAQueryFactory jpaFactory;

    private static final QUserPO USER_PO = QUserPO.userPO;

    @Override
    @Transactional
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        //登录成功认证
        Authentication authentication = authenticationSuccessEvent.getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String wxId = userDetails.getUsername();
        // 更新最后登录时间
        jpaFactory.update(USER_PO).set(USER_PO.lastLogin, new Date()).where(USER_PO.wxId.eq(wxId)).execute();
    }
}
