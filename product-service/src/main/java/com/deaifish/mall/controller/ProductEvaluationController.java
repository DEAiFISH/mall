package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.ProductEvaluationDTO;
import com.deaifish.mall.pojo.vo.ProductEvaluationVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductEvaluationService;
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
 * @date 2024/12/28 20:15
 */
@RestController
@RequestMapping("/product-evaluation/v1")
@Tag(name = "商品评价管理")
@Validated
public class ProductEvaluationController {
    @Resource
    private ProductEvaluationService productEvaluationService;

    /**
     * 根据商品id查询评价列表
     * @param pId
     * @return
     */
    @GetMapping("/list/{pId}")
    public R<List<ProductEvaluationVO>> listByProductId(@PathVariable(name = "pId") @Parameter(description = "商品id") Long pId) {
        return R.success("查询成功", productEvaluationService.listByProductId(pId));
    }

    /**
     * 添加评价
     * @param productEvaluationDTO
     * @return
     */
    @PostMapping("/add")
    public R<ProductEvaluationVO> add(@RequestBody @Validated ProductEvaluationDTO productEvaluationDTO) {
        return R.success("添加成功", productEvaluationService.add(productEvaluationDTO));
    }

    /**
     * 更新评价
     * @param productEvaluationDTO
     * @return
     */
    @PutMapping("/update")
    public R<ProductEvaluationVO> update(@RequestBody @Validated(UpdateGroup.class) ProductEvaluationDTO productEvaluationDTO) {
        return R.success("修改成功", productEvaluationService.update(productEvaluationDTO));
    }

    /**
     * 删除评价
     * @param evaluationId
     * @return
     */
    @DeleteMapping("/delete/{evaluationId}")
    public R<Boolean> delete(@PathVariable(value = "evaluationId") @Parameter(description = "评价id") Long evaluationId) {
        productEvaluationService.delete(evaluationId);
        return R.success("删除成功", true);
    }
}
