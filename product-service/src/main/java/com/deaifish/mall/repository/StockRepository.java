package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.StockPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 21:16
 */
@Repository
public interface StockRepository extends JpaRepository<StockPO, Long> {
}
