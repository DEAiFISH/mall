package com.deaifish.mall.aop;

import com.deaifish.mall.pojo.po.BasePO;
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
@Component
public class JpaRepositoryAOP {

    @Before("execution(* org.springframework.data.repository.CrudRepository.save(..))")
    public void beforeSave(JoinPoint joinPoint) {
        BasePO arg = (BasePO) joinPoint.getArgs()[0];
        arg.setCreateTime(new Date());
        arg.setUpdateTime(new Date());
    }
}