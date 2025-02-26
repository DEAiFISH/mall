package com.deaifish.mall.controller;

import com.deaifish.mall.group.ADDGroup;
import com.deaifish.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.pojo.vo.UserBrowseHistoryVO;
import com.deaifish.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.LabelService;
import com.deaifish.mall.service.UserBrowseHistoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

/**
 * @description 用户服务内网接口
 *
 * @author DEAiFISH
 * @date 2025/2/19 17:05
 */
@RestController
@RequestMapping("/user-service/v1")
@Tag(name = "用户服务内网接口")
@Validated
@RequiredArgsConstructor
public class UserServiceApiController {

    private final LabelService labelService;
    private final UserBrowseHistoryService userBrowseHistoryService;

    /**
     * 用户兴趣度列表
     * @param userID
     * @return
     */
    @GetMapping("/interest/list/{uId}")
    public R<List<UserInterestVO>> interestList(@PathVariable("uId") @Parameter(description = "用户id") Long userID) {
        return R.success("查询成功", labelService.interestList(userID));
    }

    /**
     * 添加用户浏览记录
     * @param userBrowseHistoryDTO
     * @return
     */
    @PostMapping("/history/add")
    public R<UserBrowseHistoryVO> historyAdd(@RequestBody @Validated(ADDGroup.class) UserBrowseHistoryDTO userBrowseHistoryDTO) {
        return R.success("添加成功", userBrowseHistoryService.add(userBrowseHistoryDTO));
    }
}