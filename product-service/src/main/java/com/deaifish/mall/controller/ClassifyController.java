package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.dto.ClassifyDTO;
import com.deaifish.mall.pojo.vo.ClassifyVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ClassifyService;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 17:16
 */
@RestController
@RequestMapping("/classify/v1")
@Tag(name = "商品分类管理")
@Validated
public class ClassifyController {
    @Resource
    private ClassifyService classifyService;

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/list")
    public R<List<ClassifyVO>> list() {
        return R.success(classifyService.list());
    }

    /**
     * 添加分类
     * @param classifyDTO
     * @return
     */
    @PostMapping("/add")
    public R<ClassifyVO> add(@RequestBody @Validated ClassifyDTO classifyDTO) {
        return R.success(classifyService.add(classifyDTO));
    }

    /**
     * 更新分类
     * @param classifyDTO
     * @return
     */
    @PutMapping("/update")
    public R<ClassifyVO> update(@RequestBody @Validated(UpdateGroup.class) ClassifyDTO classifyDTO) {
        return R.success(classifyService.update(classifyDTO));
    }

    /**
     * 删除分类
     * @param classifyId
     * @return
     */
    @DeleteMapping("/delete/{classifyId}")
    public R<Boolean> delete(@PathVariable(value = "classifyId") @Parameter(description = "商品分类id") Integer classifyId) {
        classifyService.delete(classifyId);
        return R.success(true);
    }
}
