package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 21:22
 */
public interface StockService {
    /**
     * 根据商品ID查询库存信息
     * @param pId
     * @return
     */
    StockVO getStockByProductId(Long pId);

    /**
     * 添加库存信息
     * @param stockDTO
     * @return
     */
    StockVO addStock(StockDTO stockDTO);

    /**
     * 更新库存信息
     * @param stockDTO
     * @return
     */
    StockVO updateStock(StockDTO stockDTO);

    /**
     * 删除库存信息
     * @param stockId
     */
    void deleteStock(Long stockId);
}
