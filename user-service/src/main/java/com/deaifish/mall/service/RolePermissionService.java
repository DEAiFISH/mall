package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.RolePermissionDTO;
import com.deaifish.mall.pojo.qo.RolePermissionQO;
import com.deaifish.mall.pojo.vo.RolePermissionVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:17
 */
public interface RolePermissionService {
    /**
     * 查询所有角色权限
     * @return
     */
    List<RolePermissionVO> list(RolePermissionQO qo);

    RolePermissionVO add(RolePermissionDTO rolePermissionDTO);

    RolePermissionVO update(RolePermissionDTO rolePermissionDTO);

    Boolean delete(Byte rolePermissionId);

    Boolean deleteRolePermission(RolePermissionDTO rolePermissionDTO);
}