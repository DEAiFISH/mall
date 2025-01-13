package com.deaifish.mall.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/31 15:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 微信ID
     */
    private String wxId;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限信息
     */
    private List<GrantedAuthority> authorities;
}
