package com.deaifish.mall.controller;

import com.deaifish.mall.entity.dto.LabelDTO;
import com.deaifish.mall.entity.vo.LabelVO;
import com.deaifish.mall.entity.vo.UserInterestVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.LabelService;
import com.deaifish.mall.validation.group.ADDGroup;
import com.deaifish.mall.validation.group.UpdateGroup;
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
    public R<LabelVO> add(@RequestBody @Validated(ADDGroup.class) LabelDTO labelDTO) {
        return R.success(labelService.add(labelDTO));
    }

    /**
     * 更新标签
     * @param labelDTO
     * @return
     */
    @PutMapping("/update")
    public R<LabelVO> update(@RequestBody @Validated(UpdateGroup.class) LabelDTO labelDTO) {
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

    /**
     * 更新用户兴趣度
     * @param ids
     * @return
     */
    @PostMapping("/interest/update")
    public R<Boolean> interestUpdate(@RequestBody @Valid List<Integer> ids, @RequestParam("uId") @Parameter(description = "用户id") Long userID) {
        labelService.interestUpdate(ids, userID);
        return R.success(true);
    }


    /**
     * 用户兴趣度列表
     * @param userID
     * @return
     */
    @GetMapping("/interest/list/{uId}")
    public R<List<UserInterestVO>> interestList(@PathVariable("uId") @Parameter(description = "用户id") Long userID) {
        return R.success(labelService.interestList(userID));
    }

    /**
     * 删除用户兴趣度
     * @param userID
     * @return
     */
    @DeleteMapping("/interest/delete")
    public R<Boolean> interestDelete(@RequestParam("uId") @Parameter(description = "用户id") Long userID) {
        labelService.interestDelete(userID);
        return R.success(true);
    }
}
