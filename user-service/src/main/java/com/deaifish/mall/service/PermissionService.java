package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.PermissionDTO;
import com.deaifish.mall.pojo.qo.RolePermissionQO;
import com.deaifish.mall.pojo.vo.PermissionVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:36
 */
public interface PermissionService {
    List<PermissionVO> list(String name);

    PermissionVO add(PermissionDTO permissionDTO);

    PermissionVO update(PermissionDTO permissionDTO);

    Boolean delete(Byte id);
}