package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:57
 */
public interface ProductService {
    /**
     * 查询商品列表
     * @return
     */
    List<ProductBriefVO> list();

    /**
     * 查询商品详情
     * @param productId
     * @return
     */
    ProductVO detail(Long productId);

    /**
     * 添加商品
     * @param productdto
     * @return
     */
    ProductVO add(ProductDTO productdto);

    /**
     * 批量添加商品
     * @param list
     * @return
     */
    List<ProductVO> addBatch(@Valid List<ProductDTO> list);

    /**
     * 更新商品
     * @param productdto
     * @return
     */
    ProductVO update(ProductDTO productdto);

    /**
     * 删除商品
     * @param productId
     */
    void delete(Long productId);

}
