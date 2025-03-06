package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.bo.Point;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.HomepageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 首页展示
 *
 * @author DEAiFISH
 * @date 2025/1/12 15:38
 */
@RestController
@Tag(name = "首页展示")
@RequestMapping("/homepage/v1")
@Validated
public class HomepageController {

    @Resource
    private HomepageService homepageService;

    /**
     * 获取用户总数
     * @return
     */
    @GetMapping("/user/count")
    public R<Long> userCount() {
        return R.success("查询成功 ",homepageService.userCount());
    }

    /**
     * 获取每个月用户注册数据
     * @return
     */
    @GetMapping("/user/register/number")
    public R<Point<String,Long>> registerNumber() {
        return R.success("获取成功",homepageService.registerNumber());
    }

}