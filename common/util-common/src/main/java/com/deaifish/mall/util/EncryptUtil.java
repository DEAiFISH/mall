package com.deaifish.mall.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;

/**
 * @description 加密工具类
 *
 * @author DEAiFISH
 * @date 2024/12/18 11:08
 */
public class EncryptUtil {

    /**
     * 加密密码
     * @param value 密码明文
     * @return
     */
    public static String encode(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }
        return BCrypt.hashpw(value);
    }

    /**
     * 校验密码
     * @param rawPassword   明文密码
     * @param encodedPassword   加密密码
     * @return
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        if (StrUtil.isBlank(rawPassword) || StrUtil.isBlank(encodedPassword)) {
            return false;
        }
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
