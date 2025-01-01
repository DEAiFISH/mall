package com.deaifish.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/1 14:44
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    /**
     * 密钥
     */
    private String secret = "my_secret";

    /**
     * 过期时间,单位为秒,默认1800秒
     **/
    private long expiration = 1800L;

    /**
     * 排除路径，默认值：/loginPage, /doLogin
     */
    private List<String> excludePath = new ArrayList<>() {{
        add("/loginPage");
        add("/doLogin");
    }};
}
