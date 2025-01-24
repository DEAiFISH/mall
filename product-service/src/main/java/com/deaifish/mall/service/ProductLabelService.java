package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ProductLabelDTO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductLabelVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:40
 */
public interface ProductLabelService {
    /**
     * 根据标签id查询商品列表
     * @param lId
     * @return
     */
    List<ProductBriefVO> listByLabelId(Integer lId);

    /**
     * 根据商品id查询标签列表
     * @param pId
     * @return
     */
    List<LabelVO> listByProductId(Long pId);

    /**
     * 添加商品标签
     * @param productLabelDTO
     * @return
     */
    ProductLabelVO add(ProductLabelDTO productLabelDTO);

    /**
     * 更新商品标签
     * @param productLabelDTO
     * @return
     */
    ProductLabelVO update(ProductLabelDTO productLabelDTO);

    /**
     * 删除商品标签
     * @param pId
     * @param lId
     */
    void delete(Long pId,Integer lId);
}
