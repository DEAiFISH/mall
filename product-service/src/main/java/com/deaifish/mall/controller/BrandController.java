package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.BrandDTO;
import com.deaifish.mall.pojo.qo.BrandQO;
import com.deaifish.mall.pojo.vo.BrandVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.BrandService;
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
 * @date 2024/12/28 15:48
 */
@RestController
@RequestMapping("/brand/v1")
@Tag(name = "商品品牌管理")
@Validated
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @param qo
     * @return
     */
    @GetMapping("/list")
    public R<List<BrandVO>> list(@ModelAttribute BrandQO qo) {
        return R.success("查询成功", brandService.list(qo));
    }

    /**
     * 添加品牌
     * @param brandDTO
     * @return
     */
    @PostMapping("/add")
    public R<BrandVO> add(@RequestBody @Validated BrandDTO brandDTO) {
        return R.success("添加成功", brandService.add(brandDTO));
    }

    /**
     * 更新品牌
     * @param brandDTO
     * @return
     */
    @PutMapping("/update")
    public R<BrandVO> update(@RequestBody @Validated(UpdateGroup.class) BrandDTO brandDTO) {
        return R.success("修改成功", brandService.update(brandDTO));
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "品牌id") Integer id) {
        brandService.delete(id);
        return R.success("删除成功", true);
    }
}
