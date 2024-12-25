package com.deaifish.mall.controller;

import com.deaifish.mall.entity.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.entity.vo.UserBrowseHistoryVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.UserBrowseHistoryService;
import com.deaifish.mall.validation.group.ADDGroup;
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
        return R.success(userBrowseHistoryService.list(uId));
    }

    /**
     * 添加用户浏览记录
     * @param userBrowseHistoryDTO
     * @return
     */
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody @Validated(ADDGroup.class) UserBrowseHistoryDTO userBrowseHistoryDTO) {
        userBrowseHistoryService.add(userBrowseHistoryDTO);
        return R.success(true);
    }

    /**
     * 删除用户浏览记录
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public R<Boolean> delete(@RequestParam("ids") List<Long> ids) {
        userBrowseHistoryService.delete(ids);
        return R.success(true);
    }
}