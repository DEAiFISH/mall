package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ProductEvaluationDTO;
import com.deaifish.mall.pojo.vo.ProductEvaluationVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:16
 */
public interface ProductEvaluationService {
    List<ProductEvaluationVO> listByProductId(Long pId);

    ProductEvaluationVO add(ProductEvaluationDTO productEvaluationDTO);

    ProductEvaluationVO update(ProductEvaluationDTO productEvaluationDTO);

    void delete(Long evaluationId);
}
