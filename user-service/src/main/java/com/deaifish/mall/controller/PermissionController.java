package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.annotation.RequiresRole;
import com.deaifish.mall.pojo.dto.PermissionDTO;
import com.deaifish.mall.pojo.vo.PermissionVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.PermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:36
 */
@RestController()
@RequestMapping("/permission/v1")
@Tag(name = "角色权限接口")
@Validated
@RequiresRole("ADMIN")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    /**
     * 查询角色权限列表
     * @return
     */
    @GetMapping("/list")
    public R<List<PermissionVO>> list() {
        return R.success("查询成功",permissionService.list());
    }

    /**
     * 添加角色权限
     * @param permissionDTO
     * @return
     */
    @PutMapping("/add")
    public R<PermissionVO> add(@Validated @RequestBody PermissionDTO permissionDTO) {
        return R.success("添加成功",permissionService.add(permissionDTO));
    }

    /**
     * 更新角色权限
     * @param permissionDTO
     * @return
     */
    @PutMapping("/update")
    public R<PermissionVO> update(@Validated @RequestBody PermissionDTO permissionDTO) {
        return R.success("更新成功",permissionService.update(permissionDTO));
    }

    /**
     * 删除角色权限
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam Byte id) {
        return R.success("删除成功",permissionService.delete(id));
    }
}