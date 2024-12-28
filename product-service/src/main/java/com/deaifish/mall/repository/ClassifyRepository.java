package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.BrandPO;
import com.deaifish.mall.pojo.po.ClassifyPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 15:54
 */
@Repository
public interface ClassifyRepository extends JpaRepository<ClassifyPO, Long> {
}
