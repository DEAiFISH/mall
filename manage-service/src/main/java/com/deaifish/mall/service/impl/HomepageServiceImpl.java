package com.deaifish.mall.service.impl;

import com.deaifish.mall.pojo.bo.Point;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.service.HomepageService;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 首页管理
 *
 * @author DEAiFISH
 * @date 2025/1/12 15:39
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class HomepageServiceImpl implements HomepageService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    private static final QUserPO USER_PO = QUserPO.userPO;
    @Override
    public List<Point<String,Long>> registerNumber() {

        StringTemplate template = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", USER_PO.createTime, "%Y-%m");
        List<Tuple> list = jpaQueryFactory.select(template.as("x"), USER_PO.userId.count().as("y"))
                .from(USER_PO)
                .groupBy(template)
                .orderBy(template.asc())
                .fetch();

        List<String> x = new ArrayList<>();
        List<Long> y = new ArrayList<>();

        list.forEach(tuple -> {
            x.add(tuple.get(0,String.class));
            y.add(tuple.get(1,Long.class));
        });

        return List.of(new Point<>(x,y));
    }
}