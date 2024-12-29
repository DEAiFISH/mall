package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.ProductLabelDTO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductLabelVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductLabelService;
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
 * @date 2024/12/28 20:39
 */
@RestController
@RequestMapping("/product-label/v1")
@Tag(name = "商品标签管理")
@Validated
public class ProductLabelController {
    @Resource
    private ProductLabelService productService;

    /**
     * 根据标签id查询商品列表
     * @param lId
     * @return
     */
    @GetMapping("/list/label/{lId}")
    public R<List<ProductBriefVO>> listByLabelId(@PathVariable("lId") @Parameter(description = "标签id") Integer lId) {
        return R.success(productService.listByLabelId(lId));
    }

    /**
     * 根据商品id查询标签列表
     * @param pId
     * @return
     */
    @GetMapping("/list/product/{pId}")
    public R<List<LabelVO>> listByProductId(@PathVariable("pId") @Parameter(description = "商品id") Long pId) {
        return R.success(productService.listByProductId(pId));
    }

    /**
     * 添加商品标签
     * @param productLabelDTO
     * @return
     */
    @PostMapping("/add")
    public R<ProductLabelVO> add(@RequestBody @Validated ProductLabelDTO productLabelDTO) {
        return R.success(productService.add(productLabelDTO));
    }

    /**
     * 更新商品标签
     * @param productLabelDTO
     * @return
     */
    @PutMapping("/update")
    public R<ProductLabelVO> update(@RequestBody @Validated(UpdateGroup.class) ProductLabelDTO productLabelDTO) {
        return R.success(productService.update(productLabelDTO));
    }

    /**
     * 删除商品标签
     * @param plId
     * @return
     */
    @DeleteMapping("/delete/{plId}")
    public R<Boolean> delete(@PathVariable("plId") @Parameter(description = "商品标签id") Long plId) {
        productService.delete(plId);
        return R.success(true);
    }
}
