package com.deaifish.mall.po;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/31 16:56
 */
public class UserNamePasswordUserAuthToken extends User {
    private String token;

    public UserNamePasswordUserAuthToken(String username, String password, Collection<? extends GrantedAuthority> authorities, String token) {
        super(username, password, authorities);
        this.token = token;
    }

    public UserNamePasswordUserAuthToken(String username, String password, String token, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
