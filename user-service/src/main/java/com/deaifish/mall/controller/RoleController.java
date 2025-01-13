package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.annotation.RequiresRole;
import com.deaifish.mall.pojo.dto.RoleDTO;
import com.deaifish.mall.pojo.vo.RoleVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/role/v1")
@Tag(name = "用户角色接口")
@Validated
@RequiresRole("ADMIN")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/list")
    public R<List<RoleVO>> list() {
        return R.success("查询成功",roleService.list());
    }

    /**
     * 添加角色
     * @param roleDTO
     * @return
     */
    @PostMapping("/add")
    public R<RoleVO> add(@Valid @RequestBody RoleDTO roleDTO) {
        return R.success("添加成功",roleService.add(roleDTO));
    }

    /**
     * 更新角色信息
     * @param roleDTO
     * @return
     */
    @PutMapping("/update")
    public R<RoleVO> update(@Valid @RequestBody RoleDTO roleDTO) {
        return R.success("更新成功",roleService.update(roleDTO));
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam("id") Byte id) {
        return R.success("删除成功",roleService.delete(id));
    }
}