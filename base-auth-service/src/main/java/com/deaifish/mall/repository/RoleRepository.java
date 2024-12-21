package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.RolePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:54
 */
@Repository
public interface RoleRepository extends JpaRepository<RolePO, Long> {

}
