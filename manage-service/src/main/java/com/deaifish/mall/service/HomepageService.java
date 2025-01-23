package com.deaifish.mall.service;

import com.deaifish.mall.pojo.bo.Point;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/12 15:39
 */
public interface HomepageService {

    /**
     * 获取每个月用户注册数据
     * @return
     */
    List<Point<String,Long>> registerNumber();
}