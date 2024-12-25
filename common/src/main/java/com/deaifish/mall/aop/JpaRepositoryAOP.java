package com.deaifish.mall.aop;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description aop
 *
 * @author DEAiFISH
 * @date 2024/12/24 15:09
 */
@Aspect
@Slf4j
@Component
public class JpaRepositoryAOP {

    @Before("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void beforeSave(JoinPoint joinPoint){
        log.info("========= before save");
        Object args = joinPoint.getArgs()[0];
        ReflectUtil.setFieldValue(args,"createTime",new Date());
        ReflectUtil.setFieldValue(args,"updateTime",new Date());
    }
}