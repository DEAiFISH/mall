package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.RoleDTO;
import com.deaifish.mall.pojo.vo.RoleVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:18
 */
public interface RoleService {
    /**
     * 查询所有角色信息
     * @return
     */
    List<RoleVO> list(String name);

    /**
     * 添加角色信息
     * @param roleDTO
     * @return
     */
    RoleVO add(@Valid RoleDTO roleDTO);

    /**
     * 更新角色信息
     * @param roleDTO
     * @return
     */
    RoleVO update(@Valid RoleDTO roleDTO);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    Boolean delete(Byte roleId);

}