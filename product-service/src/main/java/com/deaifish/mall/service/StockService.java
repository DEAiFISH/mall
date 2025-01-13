package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 21:22
 */
public interface StockService {

    /**
     * 查询所有库存信息
     * @return
     */
    List<StockVO> listStock();

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

    /**
     * 创建库存信息
     * @param productId
     * @param amount
     */
    void createStock(Long productId, Integer amount);
}
