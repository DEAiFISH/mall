package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/7 21:55
 */
@Repository
public interface PermissionRepository extends JpaRepository<PermissionPO, Long> {
}
