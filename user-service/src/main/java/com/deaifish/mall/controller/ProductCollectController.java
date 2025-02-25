package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.vo.ProductCollectVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductCollectService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:09
 */
@RestController
@RequestMapping("/product-collect/v1")
@Tag(name = "商品收藏接口")
@Validated
public class ProductCollectController {
    @Resource
    private ProductCollectService productCollectService;

    /**
     * 查询用户收藏的商品列表
     *
     * @param uId 用户id
     * @return R<List < ProductCollectVO>>
     */
    @GetMapping("/list/{uId}")
    public R<List<ProductCollectVO>> list(@PathVariable("uId") @Parameter(description = "用户id") Long uId) {
        return R.success("查询成功", productCollectService.list(uId));
    }

    /**
     * 查询用户是否收藏了某个商品
     *
     * @param uId   用户id
     * @param pId   商品id
     * @return Boolean
     */
    @GetMapping("/is-collect/{uId}/{pId}")
    public R<Boolean> isCollect(@PathVariable("uId") @Parameter(description = "用户id") Long uId,
                                @Parameter(description = "商品id") @PathVariable("pId") Long pId) {
        return R.success("查询成功", productCollectService.isCollect(uId, pId));
    }

    /**
     * 查询用户收藏的商品数量
     * @param uId 用户id
     * @return
     */
    @GetMapping("/count/{userId}")
    public R<Long> countCollect(@PathVariable("userId") @Parameter(description = "用户id") Long uId) {
        return R.success("查询成功", productCollectService.count(uId));
    }

    /**
     * 收藏商品
     *
     * @param uId   用户id
     * @param pId   商品id
     * @return Boolean
     */
    @PostMapping("/collect")
    public R<Boolean> collect(@RequestParam("uId") @Parameter(description = "用户id") Long uId,
                              @RequestParam("pId") @Parameter(description = "商品id") Long pId) {
        return R.success("收藏成功", productCollectService.collect(uId, pId));
    }

    /**
     * 取消收藏商品
     *
     * @param uId   用户id
     * @param pId   商品id
     * @return Boolean
     */
    @DeleteMapping("/cancel-collect")
    public R<Boolean> cancelCollect(@RequestParam("uId") @Parameter(description = "用户id") Long uId,
                                    @RequestParam("pId") @Parameter(description = "商品id") Long pId) {
        return R.success("取消收藏成功", productCollectService.cancelCollect(uId, pId));
    }

}