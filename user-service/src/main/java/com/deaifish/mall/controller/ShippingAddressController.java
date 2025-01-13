package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.dto.ShippingAddressDTO;
import com.deaifish.mall.pojo.vo.ShippingAddressVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ShippingAddressService;
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
 * @date 2024/12/24 22:08
 */
@RestController
@Tag(name = "收货地址管理接口")
@RequestMapping("/shipping-address/v1")
@Validated
public class ShippingAddressController {
    @Resource
    private ShippingAddressService shippingAddressService;

    /**
     * 查询该用户所有收货地址
     *
     * @param uId
     */
    @GetMapping("/list/{uId}")
    public R<List<ShippingAddressVO>> listAll(@PathVariable("uId") @Parameter(description = "用户id") Long uId) {
        return R.success("查询成功", shippingAddressService.listAll(uId));
    }

    /**
     * 添加收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    @PostMapping("/add")
    public R<ShippingAddressVO> add(@RequestBody @Valid ShippingAddressDTO shippingAddressDTO) {
        return R.success("添加成功", shippingAddressService.add(shippingAddressDTO));
    }

    /**
     * 更新收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    @PutMapping("/update")
    public R<ShippingAddressVO> update(@RequestBody @Valid ShippingAddressDTO shippingAddressDTO) {
        return R.success("修改成功", shippingAddressService.update(shippingAddressDTO));
    }

    /**
     * 删除收货地址
     * @param saId
     * @return
     */
    @DeleteMapping("/delete/{saId}")
    public R<Boolean> delete(@PathVariable("saId") @Parameter(description = "收货地址id") Long saId) {
        return R.success("删除成功", shippingAddressService.delete(saId));
    }
}