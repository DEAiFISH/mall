package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.RolePermissionDTO;
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
    List<RolePermissionVO> list();

    RolePermissionVO add(RolePermissionDTO rolePermissionDTO);

    List<RolePermissionVO> addBatch(List<RolePermissionDTO> rolePermissionDTOs);

    RolePermissionVO update(RolePermissionDTO rolePermissionDTO);

    Boolean delete(Byte rolePermissionId);
}