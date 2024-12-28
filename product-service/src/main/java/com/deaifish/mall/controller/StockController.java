package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.StockService;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 21:17
 */
@RestController
@RequestMapping("/stock/v1")
@Tag(name = "商品库存管理")
@Validated
public class StockController {
    @Resource
    private StockService stockService;

    @GetMapping("/get/{pId}")
    public R<StockVO> getStockByProductId(@PathVariable("pId") @Parameter(description = "商品id") Long pId) {
        return R.success(stockService.getStockByProductId(pId));
    }

    @PostMapping("/add")
    public R<StockVO> addStock(@RequestBody @Validated StockDTO stockDTO) {
        return R.success(stockService.addStock(stockDTO));
    }

    @PutMapping("/update")
    public R<StockVO> updateStock(@RequestBody @Validated(UpdateGroup.class) StockDTO stockDTO) {
        return R.success(stockService.updateStock(stockDTO));
    }

    @DeleteMapping("/delete/{stockId}")
    public R<Boolean> deleteStock(@PathVariable("stockId") Long stockId) {
        stockService.deleteStock(stockId);
        return R.success(true);
    }


}
