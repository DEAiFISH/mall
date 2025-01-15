package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    /**
     * 查询库存列表
     * @return
     */
    @GetMapping("/list")
    public R<List<StockVO>> createStock(){
        return R.success("查询成功", stockService.listStock());
    }

    /**
     * 添加库存
     * @param stockDTO
     * @return
     */
    @PostMapping("/add")
    public R<StockVO> addStock(@RequestBody @Validated StockDTO stockDTO) {
        return R.success("添加成功", stockService.addStock(stockDTO));
    }

    /**
     * 修改库存
     * @param stockDTO
     * @return
     */
    @PutMapping("/update")
    public R<StockVO> updateStock(@RequestBody @Validated(UpdateGroup.class) StockDTO stockDTO) {
        return R.success("修改成功", stockService.updateStock(stockDTO));
    }

    /**
     * 删除库存
     * @param stockId
     * @return
     */
    @DeleteMapping("/delete/{stockId}")
    public R<Boolean> deleteStock(@PathVariable("stockId") Long stockId) {
        stockService.deleteStock(stockId);
        return R.success("删除成功", true);
    }

}
