package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.SearchKeywordDTO;
import com.deaifish.mall.pojo.vo.SearchKeywordVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.SearchKeywordService;
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
 * @date 2025/1/5 22:59
 */
@RestController
@Tag(name = "搜索关键字服务")
@RequestMapping("/search-keyword/v1")
@Validated
public class SearchKeywordController {
    @Resource
    private SearchKeywordService searchKeywordService;

    /**
     * 查询所有搜索关键字
     * @return
     */
    @GetMapping("/list")
    public R<List<SearchKeywordVO>> list() {
        return R.success("查询成功", searchKeywordService.list());
    }

    /**
     * 添加搜索关键字
     * @param searchKeywordVO
     * @return
     */
    @PostMapping("/add")
    public R<SearchKeywordVO> add(@RequestBody @Validated SearchKeywordDTO searchKeywordVO) {
        return R.success("添加成功", searchKeywordService.add(searchKeywordVO));
    }

    /**
     * 更新搜索关键字
     * @param searchKeywordVO
     * @return
     */
    @PutMapping("/update")
    public R<SearchKeywordVO> update(@RequestBody @Validated(UpdateGroup.class) SearchKeywordDTO searchKeywordVO) {
        return R.success("修改成功", searchKeywordService.update(searchKeywordVO));
    }

    /**
     * 搜索关键字 更新点击量
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    public R<SearchKeywordVO> updateAmount(@PathVariable("id") @Parameter(description = "搜索关键词id") Long id) {
        return R.success("修改成功", searchKeywordService.updateAmount(id));
    }

    /**
     * 删除搜索关键字
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "搜索关键字id") Long id) {
        searchKeywordService.delete(id);
        return R.success("删除成功", true);
    }
}