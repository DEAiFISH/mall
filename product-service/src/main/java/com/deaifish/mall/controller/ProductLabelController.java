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
        return R.success("查询成功", productService.listByLabelId(lId));
    }

    /**
     * 根据商品id查询标签列表
     * @param pId
     * @return
     */
    @GetMapping("/list/product/{pId}")
    public R<List<LabelVO>> listByProductId(@PathVariable("pId") @Parameter(description = "商品id") Long pId) {
        return R.success("查询成功", productService.listByProductId(pId));
    }

    /**
     * 添加商品标签
     * @param productLabelDTO
     * @return
     */
    @PostMapping("/add")
    public R<ProductLabelVO> add(@RequestBody @Validated ProductLabelDTO productLabelDTO) {
        return R.success("添加成功", productService.add(productLabelDTO));
    }

    /**
     * 更新商品标签
     * @param productLabelDTO
     * @return
     */
    @PutMapping("/update")
    public R<ProductLabelVO> update(@RequestBody @Validated(UpdateGroup.class) ProductLabelDTO productLabelDTO) {
        return R.success("修改成功", productService.update(productLabelDTO));
    }

    /**
     * 删除商品标签
     * @param pId
     * @param lId
     * @return
     */
    @DeleteMapping("/delete/{pId}/{lId}")
    public R<Boolean> delete(@PathVariable("pId") @Parameter(description = "商品id") Long pId,
                             @PathVariable("lId") @Parameter(description = "标签id") Integer lId) {
        productService.delete(pId, lId);
        return R.success("删除成功", true);
    }
}
