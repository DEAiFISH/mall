package com.deaifish.mall.controller;

import com.deaifish.mall.entity.dto.LabelDTO;
import com.deaifish.mall.entity.vo.LabelVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.LabelService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "标签管理")
@RequestMapping("/user-label/v1")
@Validated
public class LabelController {

    @Resource
    private LabelService labelService;


    /**
     * 标签列表
     * @return
     */
    @RequestMapping("/list")
    public R<List<LabelVO>> list() {
        return R.success(labelService.list());
    }

    /**
     * 添加标签
     * @param labelDTO
     * @return
     */
    @PostMapping("/add")
    public R<LabelVO> add(@RequestBody @Valid LabelDTO labelDTO) {
        return R.success(labelService.add(labelDTO));
    }

    /**
     * 更新标签
     * @param labelDTO
     * @return
     */
    @PutMapping("/update")
    public R<LabelVO> update(@RequestBody @Valid LabelDTO labelDTO) {
        return R.success(labelService.update(labelDTO));
    }

    /**
     * 删除标签
     * @param labelId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "标签id") Integer labelId) {
        labelService.delete(labelId);
        return R.success(true);
    }
}
