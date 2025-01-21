package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.annotation.RequiresRole;
import com.deaifish.mall.pojo.dto.RolePermissionDTO;
import com.deaifish.mall.pojo.qo.RolePermissionQO;
import com.deaifish.mall.pojo.vo.RolePermissionVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.RolePermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:13
 */
@RestController()
@RequestMapping("/role-permission/v1")
@Tag(name = "用户角色权限对应接口")
@Validated
@RequiresRole("ADMIN")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    /**
     * 查询角色权限列表
     * @return
     */
    @GetMapping("/list")
    public R<List<RolePermissionVO>> list(@ModelAttribute("qo") RolePermissionQO qo) {
        return R.success("查询成功",rolePermissionService.list(qo));
    }

    /**
     * 添加角色权限
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/add")
    public R<RolePermissionVO> add(@Validated @RequestBody RolePermissionDTO rolePermissionDTO) {
        return R.success("添加成功",rolePermissionService.add(rolePermissionDTO));
    }

    /**
     * 更新角色权限
     * @param rolePermissionDTO
     * @return
     */
    @PutMapping("/update")
    public R<RolePermissionVO> update(@Validated(UpdateGroup.class) @RequestBody RolePermissionDTO rolePermissionDTO) {
        return R.success("更新成功",rolePermissionService.update(rolePermissionDTO));
    }

    /**
     * 删除角色权限
     * @param rolePermissionId
     * @return
     */
    @DeleteMapping("/delete/{rpId}")
    public R<Boolean> delete(@PathVariable("rpId") Byte rolePermissionId) {
        return R.success("删除成功",rolePermissionService.delete(rolePermissionId));
    }

    @DeleteMapping("/delete")
    public R<Boolean> deleteRolePermission(@Validated @RequestBody RolePermissionDTO rolePermissionDTO) {
        return R.success("删除成功",rolePermissionService.deleteRolePermission(rolePermissionDTO));
    }
}