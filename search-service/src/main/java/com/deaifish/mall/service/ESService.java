package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.qo.ProductESQO;
import com.deaifish.mall.pojo.vo.ProductESVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/7 14:09
 */
public interface ESService {

    void saveProduct(ProductESDTO productDTO);

    void saveProductBatch(List<ProductESDTO> productDTOList);

    void deleteProductById(long pId);

    void deleteProductByNumber(List<Long> ids);

    List<ProductESVO> searchProduct(ProductESQO qo, Pageable page);
}