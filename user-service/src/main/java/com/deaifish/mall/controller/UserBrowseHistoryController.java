package com.deaifish.mall.controller;

import com.deaifish.mall.group.ADDGroup;
import com.deaifish.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.pojo.vo.UserBrowseHistoryVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.UserBrowseHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/25 0:17
 */

@RestController
@Tag(name = "用户来浏览记录管理接口")
@RequestMapping("/browse-history/v1")
@Validated
public class UserBrowseHistoryController {
    @Resource
    private UserBrowseHistoryService userBrowseHistoryService;

    /**
     * 查询用户浏览记录
     * @param uId 用户id
     * @return
     */
    @GetMapping("/list/{uId}")
    public R<List<UserBrowseHistoryVO>> list(@PathVariable(name = "uId") Long uId) {
        return R.success("查询成功", userBrowseHistoryService.list(uId));
    }

    /**
     * 添加用户浏览记录
     * @param userBrowseHistoryDTO
     * @return
     */
    @PostMapping("/add")
    public R<UserBrowseHistoryVO> add(@RequestBody @Validated(ADDGroup.class) UserBrowseHistoryDTO userBrowseHistoryDTO) {
        return R.success("添加成功", userBrowseHistoryService.add(userBrowseHistoryDTO));
    }

    /**
     * 删除用户浏览记录
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam("ids") List<Long> ids) {
        return R.success("删除成功", userBrowseHistoryService.delete(ids));
    }
}