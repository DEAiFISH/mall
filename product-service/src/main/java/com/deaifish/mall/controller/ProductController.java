package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.qo.ProductQO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
     * @param qo
     * @return
     */
    @GetMapping("/list")
    public R<List<ProductBriefVO>> list(@ModelAttribute ProductQO qo) {
        return R.success("查询成功", productService.list(qo));
    }

    /**
     * 查询商品详情
     */
    @GetMapping("/detail/{productId}")
    public R<ProductVO> detail(@PathVariable(name = "productId") @Parameter(description = "商品id") Long productId) {
        return R.success("查询成功", productService.detail(productId));
    }

    /**
     * 首页商品列表展示
     * @return
     */
    @GetMapping("/list/homepage")
    public R<List<ProductVO>> listHomepage() {
        return R.success("查询成功", productService.listHomepage());
    }

    /**
     * 添加商品
     * @param productdto
     * @return
     */
    @PostMapping("/add")
    public R<ProductVO> add(@RequestBody @Validated ProductDTO productdto) {
        return R.success("添加成功", productService.add(productdto));
    }

    /**
     * 批量添加商品
     * @param list
     * @return
     */
    @PostMapping("/addBatch")
    public R<List<ProductVO>> addBatch(@RequestBody @Validated List<ProductDTO> list) {
        return R.success("添加成功", productService.addBatch(list));
    }

    /**
     * 更新商品
     * @param productdto
     * @return
     */
    @PutMapping("/update")
    public R<ProductVO> update(@RequestBody @Validated(UpdateGroup.class) ProductDTO productdto) {
        return R.success("修改成功", productService.update(productdto));
    }

    /**
     * 删除商品
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{productId}")
    public R<Boolean> delete(@PathVariable(name = "productId") Long productId) {
        productService.delete(productId);
        return R.success("删除成功", true);
    }
}
