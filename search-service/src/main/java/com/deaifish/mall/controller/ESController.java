package com.deaifish.mall.controller;

import com.deaifish.mall.mapper.ProductESMapper;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.qo.ProductESQO;
import com.deaifish.mall.pojo.vo.ProductESVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ESService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/7 14:07
 */
@RestController
@RequestMapping("/es/v1")
@Tag(name = "搜索管理")
@Validated
public class ESController {

    @Resource
    private ESService esService;
    @Resource
    private ProductESMapper productESMapper;

    @GetMapping("/test")
    public String test() {
        productESMapper.deleteIndex("product_es");
        productESMapper.createIndex();
        return "test";
    }

    /**
     * 添加商品到ES
     * @param productDTO
     * @return
     */
    @PostMapping("/save/product")
    public R<Boolean> saveProduct(@RequestBody @Valid ProductESDTO productDTO) {
        esService.saveProduct(productDTO);
        return R.success("添加成功", true);
    }

    /**
     * 批量添加商品到ES
     * @param productDTOList
     * @return
     */
    @PostMapping("/save/product/batch")
    public R<Boolean> saveProductBatch(@RequestBody @Valid List<ProductESDTO> productDTOList) {
        esService.saveProductBatch(productDTOList);
        return R.success("添加成功", true);
    }

    /**
     * 删除商品
     * @param pId
     * @return
     */
    @DeleteMapping("/delete/product/{pId}")
    public R<Boolean> deleteProductById(@PathVariable("pId") long pId) {
        esService.deleteProductById(pId);
        return R.success("删除成功", true);
    }

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/product/batch-ids")
    public R<Boolean> deleteProductByNumber(@RequestParam("ids") List<Long> ids) {
        esService.deleteProductByNumber(ids);
        return R.success("删除成功", true);
    }

    /**
     * 搜索商品
     * @param qo
     * @param page
     * @return
     */
    @GetMapping("/search/product")
    public R<List<ProductESVO>> searchProduct(@ModelAttribute ProductESQO qo,
                                              @PageableDefault(page = 0, size = 10) Pageable page) {
        return R.success("查询成功", esService.searchProduct(qo, page));
    }
}