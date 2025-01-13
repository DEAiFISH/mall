package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.RolePermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:24
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionPO, Byte> {
}