package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.VoucherDTO;
import com.deaifish.mall.pojo.vo.VoucherVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.VoucherService;
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
@Tag(name = "优惠卷管理")
@RequestMapping("/voucher/v1")
@Validated
public class VoucherController {
    @Resource
    private VoucherService voucherService;

    /**
     * 查询所有优惠卷
     * @param name
     * @return
     */
    @GetMapping("/list")
    public R<List<VoucherVO>> list(@RequestParam("name") @Parameter(description = "优惠卷名称") String name) {
        return R.success("查询成功", voucherService.list(name));
    }

    /**
     * 添加优惠卷
     * @param voucherDTO
     * @return
     */
    @PostMapping("/add")
    public R<VoucherVO> add(@RequestBody @Validated VoucherDTO voucherDTO) {
        return R.success("添加成功", voucherService.add(voucherDTO));
    }

    /**
     * 更新优惠卷
     * @param voucherDTO
     * @return
     */
    @PutMapping("/update")
    public R<VoucherVO> update(@RequestBody @Validated(UpdateGroup.class) VoucherDTO voucherDTO) {
        return R.success("修改成功", voucherService.update(voucherDTO));
    }

    /**
     * 删除优惠卷
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "优惠卷id") Long id) {
        voucherService.delete(id);
        return R.success("删除成功", true);
    }
}