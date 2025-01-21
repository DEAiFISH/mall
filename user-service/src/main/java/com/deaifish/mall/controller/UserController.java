package com.deaifish.mall.controller;

import com.deaifish.mall.group.ADDGroup;
import com.deaifish.mall.group.UpdateGroup;
import com.deaifish.mall.pojo.dto.ResetPasswordDTO;
import com.deaifish.mall.pojo.dto.SetPaymentDTO;
import com.deaifish.mall.pojo.dto.UserDTO;
import com.deaifish.mall.pojo.qo.UserQO;
import com.deaifish.mall.pojo.vo.UserBriefVO;
import com.deaifish.mall.pojo.vo.UserDetailedVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@Validated
//@RequiresRole("ADMIN")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取所有用户信息
     * @param qo
     * @return
     */
    @GetMapping("/get/all")
//    @RequiresRole("ROLE_CUSTOMER")
    public R<List<UserBriefVO>> getAll(@ModelAttribute UserQO qo) {
        return R.success("查询成功", userService.getAll(qo));
    }

    /**
     * 获取单个用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public R<UserDetailedVO> get(@PathVariable("id") @Parameter(description = "用户id") Long id) {
        return R.success("查询成功", userService.getById(id));
    }

    /**
     * 设置用户头像
     * @param id
     * @param avatar
     * @return
     */
    @PutMapping("/set/avatar")
    public R<Boolean> setAvatar(@RequestParam("id") @Parameter(description = "用户id") Long id,
                                @RequestParam("avatar") @Parameter(description = "头像") String avatar) {
        userService.setAvatar(id, avatar);
        return R.success("修改头像成功", true);
    }

    /**
     * 修改密码
     * @param resetPasswordDTO
     * @return
     */
    @PutMapping("/set-password")
    public R<Boolean> forgetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        userService.setPassword(resetPasswordDTO);
        return R.success("修改密码成功", true);
    }

    /**
     * 修改支付密码
     * @param paymentDTO
     * @return
     */
    @PostMapping("/set/payment-password")
    public R<Boolean> setPaymentPassword(@Validated(UpdateGroup.class) @RequestBody SetPaymentDTO paymentDTO) {
        userService.setPaymentPassword(paymentDTO);
        return R.success("修改支付密码成功", true);
    }

    /**
     * 注册用户
     * @param userDTO
     * @return
     */
    @PostMapping("/sign-up")
    public R<UserDetailedVO> signUp(@RequestBody @Validated(ADDGroup.class) UserDTO userDTO) {
        return R.success("注册成功", userService.signUp(userDTO));
    }

    /**
     * 判断用户是否存在
     * @param wxId
     * @return true 存在，false 不存在
     */
    @GetMapping("/get/exists/{wxId}")
    public R<Boolean> exists(@PathVariable(name = "wxId") @Parameter(description = "微信id") String wxId) {
        return R.success("微信号已存在", userService.existsById(wxId));
    }

    /**
     * 更新用户信息
     * @param userDTO
     * @return
     */
    @PutMapping("/update")
    public R<UserDetailedVO> update(@RequestBody @Validated(UpdateGroup.class) UserDTO userDTO) {
        return R.success("修改成功", userService.update(userDTO));
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public R<Boolean> delete(@PathVariable(name = "id") @Parameter(description = "用户id") Long id) {
        userService.delete(id);
        return R.success("删除成功", true);
    }

}
