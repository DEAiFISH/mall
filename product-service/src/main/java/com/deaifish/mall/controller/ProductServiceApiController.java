package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductService;
import com.deaifish.mall.service.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description 商品服务内网接口
 *
 * @author DEAiFISH
 * @date 2025/1/15 15:35
 */
@RestController
@RequestMapping("/product-service/v1")
@Tag(name = "商品服务内网接口")
@Validated
@RequiredArgsConstructor
public class ProductServiceApiController {
    private final StockService stockService;
    private final ProductService productService;

    /**
     * 根据商品id查询商品详情
     * @param pId
     * @return
     */
    @GetMapping("/product/get/{pId}")
    public R<ProductVO> getProductById(@PathVariable("pId") @Parameter(description = "商品id") @NotNull(message = "商品id不能为空") Long pId) {
        return R.success("查询成功", productService.detail(pId));
    }

    /**
     * 减少库存
     * @param num
     * @return
     */
    @PutMapping("/stock/reduce")
    public R<Boolean> reduceStock(@RequestParam("num") @NotNull(message = "数量不能为空") Integer num,
                                  @RequestParam("pId") @NotNull(message = "商品id不能为空") Long pId) {
        return R.success("减少库存成功", stockService.reduceStock(num, pId));
    }
}