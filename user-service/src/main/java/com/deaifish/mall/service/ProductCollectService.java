package com.deaifish.mall.service;

import com.deaifish.mall.entity.vo.ProductCollectVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:14
 */
public interface ProductCollectService {
    /**
     * 查询商品收藏列表
     * @return
     */
    List<ProductCollectVO> list(Long userId);

    /**
     * 查询商品是否被收藏
     * @param uId
     * @param pId
     * @return
     */
    Boolean isCollect(Long uId, Long pId);

    /**
     * 收藏商品
     * @param uId
     * @param pId
     * @return
     */
    Boolean collect(Long uId, Long pId);

    /**
     * 取消收藏商品
     * @param uId
     * @param pId
     * @return
     */
    Boolean cancelCollect(Long uId, Long pId);
}