package com.deaifish.mall.controller;

import com.deaifish.mall.service.HomepagePresentationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 首页展示
 *
 * @author DEAiFISH
 * @date 2025/1/12 15:38
 */
@RestController
@Tag(name = "首页展示")
@RequestMapping("/homepage-presentation/v1")
@Validated
public class HomepagePresentationController {

    @Resource
    private HomepagePresentationService homepagePresentationService;

    // 首页商品推荐

}