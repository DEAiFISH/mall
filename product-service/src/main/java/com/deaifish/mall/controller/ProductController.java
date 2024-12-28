package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductService;
import com.deaifish.mall.validation.group.UpdateGroup;
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
 * @date 2024/12/28 14:54
 */
@RestController
@RequestMapping("/product/v1")
@Tag(name = "商品管理")
@Validated
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/list")
    public R<List<ProductBriefVO>> list() {
        return R.success(productService.list());
    }

    /**
     * 查询商品详情
     */
    @GetMapping("/detail/{productId}")
    public R<ProductVO> detail(@PathVariable(name = "productId") @Parameter(description = "商品id") Long productId) {
        return R.success(productService.detail(productId));
    }

    /**
     * 添加商品
     * @param productdto
     * @return
     */
    @PostMapping("/add")
    public R<ProductVO> add(@RequestBody @Validated ProductDTO productdto) {
        return R.success(productService.add(productdto));
    }

    /**
     * 批量添加商品
     * @param list
     * @return
     */
    @PostMapping("/addBatch")
    public R<List<ProductVO>> addBatch(@RequestBody @Validated List<ProductDTO> list) {
        return R.success(productService.addBatch(list));
    }

    /**
     * 更新商品
     * @param productdto
     * @return
     */
    @PutMapping("/update")
    public R<ProductVO> update(@RequestBody @Validated(UpdateGroup.class) ProductDTO productdto) {
        return R.success(productService.update(productdto));
    }

    /**
     * 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public R<Boolean> delete(@PathVariable(name = "productId") Long productId) {
        productService.delete(productId);
        return R.success(true);
    }
}
