package com.deaifish.mall.pojo.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @description 自定义用户认证信息，用于存放token
 *
 * @author DEAiFISH
 * @date 2024/12/31 16:56
 */
public class UserNamePasswordUserAuthToken extends User {
    private Long id;
    private String nickName;
    private String avatar;
    private String token;

    public UserNamePasswordUserAuthToken(String username, String password, Collection<? extends GrantedAuthority> authorities, String token) {
        super(username, password, authorities);
        this.token = token;
    }


    public UserNamePasswordUserAuthToken(Long id,String nickName, String avatar, String username, String password, String token, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
        this.id = id;
        this.nickName = nickName;
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
