package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.ProductLabelPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:41
 */
@Repository
public interface ProductLabelRepository extends JpaRepository<ProductLabelPO, Long> {
}
