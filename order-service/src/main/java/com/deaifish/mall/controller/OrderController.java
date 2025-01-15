package com.deaifish.mall.controller;

import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.qo.OrderQO;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 查询订单列表
     * @param qo
     * @return
     */
    @GetMapping("/list")
    public R<List<OrderVO>> list(@ModelAttribute OrderQO qo) {
        return R.success("查询成功", orderService.list(qo));
    }

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
     * 支付订单
     * @param orderId
     * @param paymentMethod 支付方式（0-支付宝 1-微信）
     * @return
     */
    @PutMapping("/pay/{id}")
    public R<OrderVO> pay(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                          @RequestParam("paymentMethod") @Parameter(description = "支付方式") Byte paymentMethod) {
        return R.success("支付成功", orderService.pay(orderId, paymentMethod));
    }

    /**
     * 发货订单
     * @param orderId
     * @param courierNumber 快递单号
     * @return
     */
    @PutMapping("/send/{id}")
    public R<OrderVO> send(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                           @RequestParam(name = "courierNumber") @Parameter(description = "快递单号") String courierNumber) {
        return R.success("发货成功", orderService.send(orderId, courierNumber));
    }

    /**
     * 收货订单
     * @param orderId
     * @return
     */
    @PutMapping("/receive/{id}")
    public R<OrderVO> receive(@PathVariable("id") @Parameter(description = "订单id") Long orderId) {
        return R.success("收货成功", orderService.receive(orderId));
    }

    /**
     * 完成订单
     * @param orderId
     * @return
     */
    @PutMapping("/finish/{id}")
    public R<OrderVO> finish(@PathVariable("id") @Parameter(description = "订单id") Long orderId) {
        return R.success("完成订单", orderService.finish(orderId));
    }

    /**
     * 取消订单
     * @param orderId
     * @param cancelReason 取消原因（1-超时未支付 2-退款关闭 3-买家取消）
     * @return
     */
    @PutMapping("/cancel/{id}")
    public R<OrderVO> cancel(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                             @RequestParam(name = "cancelReason") @Parameter(description = "取消原因") Byte cancelReason) {
        return R.success("取消订单", orderService.cancel(orderId, cancelReason));
    }

    /**
     * 修改订单备注信息
     * @param orderId
     * @param memos
     * @return
     */
    @PutMapping("/update/memos/{id}")
    public R<OrderVO> updateMemos(@PathVariable("id") @Parameter(description = "订单id") Long orderId,
                                  @RequestParam(name = "memos") @Parameter(description = "备注信息") String memos) {
        return R.success("修改成功", orderService.updateMemos(orderId, memos));
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
