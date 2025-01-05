package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 16:54
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderPO, Long> {
}