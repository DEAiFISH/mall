package com.deaifish.mall.controller;

import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 订单接口
 *
 * @author DEAiFISH
 * @date 2025/1/2 23:00
 */
@RestController
@RequestMapping("/order/v1")
@Tag(name = "订单接口")
@Validated
public class OrderController {
    @Resource
    private OrderService orderService;


    /**
     * 根据订单id获取订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/get/order/{id}")
    public R<OrderVO> getByOrderId(@PathVariable("id") @Parameter(description = "订单id") Long orderId) {
        return R.success("查询成功", orderService.getByOrderId(orderId));
    }

    /**
     * 根据用户id获取订单列表
     * @param userId
     * @return
     */
    @GetMapping("/get/user/{id}")
    public R<List<OrderVO>> getByUserId(@PathVariable("id") @Parameter(description = "用户id") Long userId) {
        return R.success("查询成功", orderService.getByUserId(userId));
    }

    /**
     * 根据商品id获取订单列表
     * @param productId
     * @return
     */
    @GetMapping("/get/product/{id}")
    public R<List<OrderVO>> getByProductId(@PathVariable("id") @Parameter(description = "商品id") Long productId) {
        return R.success("查询成功", orderService.getByProductId(productId));
    }

    /**
     * 更新订单信息
     * @param orderDTO
     * @return
     */
    @PutMapping("/update")
    public R<OrderVO> update(@Validated(UpdateGroup.class) @RequestBody OrderDTO orderDTO) {
        return R.success("修改成功", orderService.update(orderDTO));
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param status
     * @return
     */
    @PutMapping("/update/status/{id}")
    public R<OrderVO> updateStatus(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                                   @RequestParam(name = "status") @Parameter(description = "订单状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）") Byte status) {
        return R.success("修改成功", orderService.updateStatus(orderId, status));
    }

    /**
     * 更新订单地址
     * @param orderId
     * @param addressId
     * @return
     */
    @PutMapping("/update/address/{id}")
    public R<OrderVO> updateAddress(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                                    @RequestParam(name = "addressId") @Parameter(description = "收货地址id") Long addressId) {
        return R.success("修改成功", orderService.updateAddress(orderId, addressId));
    }

    /**
     * 添加订单信息
     * @param orderDTO
     * @return
     */
    @PostMapping("/add")
    public R<OrderVO> add(@Validated @RequestBody OrderDTO orderDTO) {
        return R.success("添加成功", orderService.add(orderDTO));
    }

    /**
     * 删除订单信息
     * @param orderId
     * @return
     */
    @PostMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable("id") @Parameter(description = "订单id") Long orderId) {
        orderService.delete(orderId);
        return R.success("删除成功", true);
    }
}
