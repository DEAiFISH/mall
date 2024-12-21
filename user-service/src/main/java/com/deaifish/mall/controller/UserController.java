package com.deaifish.mall.controller;

import com.deaifish.mall.entity.dto.SetPasswordDTO;
import com.deaifish.mall.entity.dto.SetPaymentDTO;
import com.deaifish.mall.entity.dto.UserDTO;
import com.deaifish.mall.entity.vo.UserBriefVO;
import com.deaifish.mall.entity.vo.UserDetailedVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:08
 */
@RestController()
@RequestMapping("/user/v1")
@Tag(name = "用户信息接口")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return
     */
    @GetMapping("/get/all")
    public R<List<UserBriefVO>> getAll() {
        return R.success(userService.getAll());
    }

    /**
     * 获取单个用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public R<UserDetailedVO> get(@PathVariable @Parameter(description = "用户ID") Long id) {
        return R.success(userService.getById(id));
    }

    /**
     * 修改密码
     * @param passwordDTO
     * @return
     */
    @PostMapping("/set/password")
    public R<Boolean> setPassword(@Valid @RequestBody SetPasswordDTO passwordDTO) {
        userService.setPassword(passwordDTO);
        return R.success(true);
    }

    /**
     * 修改支付密码
     * @param paymentDTO
     * @return
     */
    @PostMapping("/set/payment-password")
    public R<Boolean> setPaymentPassword(@Valid @RequestBody SetPaymentDTO paymentDTO) {
        userService.setPaymentPassword(paymentDTO);
        return R.success(true);
    }

    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @PutMapping("/sign-up")
    public R<Boolean> signUp(@RequestBody UserDTO userDTO) {
        userService.signUp(userDTO);
        return R.success(true);
    }

    /**
     * 判断用户是否存在
     * @param id
     * @return
     */
    @GetMapping("/get/exists/{id}")
    public R<Boolean> exists(@PathVariable @Parameter(description = "用户id") Long id) {
        return R.success(userService.existsById(id));
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return R.success(true);
    }

}
