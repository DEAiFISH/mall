package com.deaifish.mall.api;

import com.deaifish.mall.fallback.SearchServiceFallBack;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.qo.ProductESQO;
import com.deaifish.mall.pojo.vo.ProductESVO;
import com.deaifish.mall.response.R;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 20:59
 */
@FeignClient(value = "search-service", path = "/es/v1", fallback = SearchServiceFallBack.class)
public interface SearchServiceApi {
    @GetMapping("/test")
    public String test();

    /**
     * 添加商品到ES
     * @param productESDTO
     * @return
     */
    @PostMapping("/save/product")
    public R<Boolean> saveProduct(@RequestBody @Valid ProductESDTO productESDTO);

    /**
     * 批量添加商品到ES
     * @param esdtos
     * @return
     */
    @PostMapping("/save/product/batch")
    public R<Boolean> saveProductBatch(@RequestBody @Valid List<ProductESDTO> esdtos);

    /**
     * 删除商品
     * @param pId
     * @return
     */
    @DeleteMapping("/delete/product/{pId}")
    public R<Boolean> deleteProductById(@PathVariable("pId") long pId);

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    @DeleteMapping("/delete/product/batch-ids")
    public R<Boolean> deleteProductByNumber(@RequestParam("ids") List<Long> ids);

    /**
     * 搜索商品
     * @param qo
     * @param page
     * @return
     */
    @GetMapping("/search/product")
    public R<List<ProductESVO>> searchProduct(@ModelAttribute ProductESQO qo, @PageableDefault(page = 0, size = 10) Pageable page);
}
