package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.vo.BrandVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.BrandService;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
     * @return
     */
    @GetMapping("/list")
    public R<List<BrandVO>> list(){
        return R.success(brandService.list());
    }

    /**
     * 添加品牌
     * @param brandVO
     * @return
     */
    @PostMapping("/add")
    public R<BrandVO> add(@RequestBody @Validated BrandVO brandVO){
        return R.success(brandService.add(brandVO));
    }

    /**
     * 更新品牌
     * @param brandVO
     * @return
     */
    @PutMapping("/update")
    public R<BrandVO> update(@RequestBody @Validated(UpdateGroup.class) BrandVO brandVO){
        return R.success(brandService.update(brandVO));
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "品牌id") Integer id){
        brandService.delete(id);
        return R.success(true);
    }
}
