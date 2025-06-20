package com.deaifish.mall.config;

import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.bo.JwtUser;
import com.deaifish.mall.pojo.bo.UserNamePasswordUserAuthToken;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.util.JWTUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 自定义用户数据源
 *
 * @author DEAiFISH
 * @date 2024/12/12 15:57
 */
@Configuration
public class UserDetailsConfig implements UserDetailsService, UserDetailsPasswordService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsConfig.class);
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private JWTUtil jwtUtil;

    private static final QUserPO USER_PO = QUserPO.userPO;
    private static final QRolePO ROLE_PO = QRolePO.rolePO;
    private static final QPermissionPO PERMISSION_PO = QPermissionPO.permissionPO;
    private static final QRolePermissionPO ROLE_PERMISSION_PO = QRolePermissionPO.rolePermissionPO;

    @Override
    @Transactional
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        // 更新数据库密码
        jpaQueryFactory.update(USER_PO).set(USER_PO.password, newPassword).where(USER_PO.wxId.eq(user.getUsername())).execute();
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String wxId) throws UsernameNotFoundException {
        UserPO userPO = jpaQueryFactory.selectFrom(USER_PO).where(USER_PO.wxId.eq(wxId)).fetchOne();
        if (userPO == null) {
            throw new MallException("用户不存在");
        }
        // 获取密码
        String password = userPO.getPassword();
        // 获取角色权限
        Byte roleId = userPO.getRoleId();
        RolePO rolePO = jpaQueryFactory.select(ROLE_PO).from(ROLE_PO).where(ROLE_PO.roleId.eq(roleId)).fetchOne();

        List<Byte> permissionIds = jpaQueryFactory.select(ROLE_PERMISSION_PO.rolePermissionId).from(ROLE_PERMISSION_PO).where(ROLE_PERMISSION_PO.roleId.eq(roleId)).fetch();
        List<PermissionPO> permissions = jpaQueryFactory.selectFrom(PERMISSION_PO).where(PERMISSION_PO.permissionId.in(permissionIds)).fetch();


        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        if(rolePO != null) {
            authorities.add(new SimpleGrantedAuthority(rolePO.getName()));
            for (PermissionPO permissionPO : permissions) {
                authorities.add(new SimpleGrantedAuthority(permissionPO.getName()));
            }
        }

        // 获取用户状态
        Byte status = userPO.getStatus();

        // 生成token
        String token = jwtUtil.createToken(new JwtUser(userPO.getUserId(), wxId, password, authorities));

        // 封装用户信息
        // 返回用户信息
        return new UserNamePasswordUserAuthToken(userPO.getUserId(), userPO.getNickName(), userPO.getAvatar(), wxId, password, token,
                status == 1, true, true, true, authorities);
    }
}
