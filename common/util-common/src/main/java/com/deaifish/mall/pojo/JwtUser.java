package com.deaifish.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/31 15:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
}
