package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.UserLabelPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:05
 */
@Repository
public interface UserLabelRepository extends JpaRepository<UserLabelPO, Long> {

}
