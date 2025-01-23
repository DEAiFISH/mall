package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.HomepageCarouselDTO;
import com.deaifish.mall.pojo.vo.HomepageCarouselVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.HomepageCarouselService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 22:59
 */
@RestController
@Tag(name = "首页轮播图管理")
@RequestMapping("/homepage-carousel/v1")
@Validated
public class HomepageCarouselController {
    @Resource
    private HomepageCarouselService homepageCarouselService;

    /**
     * 查询所有轮播图
     * @param productName 商品名称
     * @return 轮播图列表
     */
    @GetMapping("/list")
    public R<List<HomepageCarouselVO>> list(@RequestParam("productName") String productName) {
        return R.success("查询成功", homepageCarouselService.list(productName));
    }

    /**
     * 添加轮播图
     * @param homepageCarouselDTO 轮播图信息
     * @return 轮播图信息
     */
    @PostMapping("/add")
    public R<HomepageCarouselVO> add(@RequestBody @Validated HomepageCarouselDTO homepageCarouselDTO) {
        return R.success("添加成功", homepageCarouselService.add(homepageCarouselDTO));
    }

    /**
     * 更新轮播图
     * @param homepageCarouselDTO 轮播图信息
     * @return 轮播图信息
     */
    @PutMapping("/update")
    public R<HomepageCarouselVO> update(@RequestBody @Validated(UpdateGroup.class) HomepageCarouselDTO homepageCarouselDTO) {
        return R.success("修改成功", homepageCarouselService.update(homepageCarouselDTO));
    }

    /**
     * 删除轮播图
     * @param id 轮播图id
     * @return 删除成功与否
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") Long id) {
        homepageCarouselService.delete(id);
        return R.success("删除成功", true);
    }

}