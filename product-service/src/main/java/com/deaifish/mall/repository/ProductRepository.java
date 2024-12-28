package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.ProductPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:58
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductPO, Long> {
}
