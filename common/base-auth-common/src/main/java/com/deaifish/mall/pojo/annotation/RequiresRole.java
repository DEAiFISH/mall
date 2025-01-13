package com.deaifish.mall.pojo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 角色校验注解，用于标记需要特定角色的方法或类。
 *
 * @author DEAiFISH
 * @date 2025/1/13 11:31
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    // 角色名数组，可以指定一个或多个角色
    String[] value() default {};
}