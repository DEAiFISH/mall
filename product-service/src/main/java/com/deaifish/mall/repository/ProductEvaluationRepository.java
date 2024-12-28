package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.ProductEvaluationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:30
 */
@Repository
public interface ProductEvaluationRepository extends JpaRepository<ProductEvaluationPO, Long> {
}
