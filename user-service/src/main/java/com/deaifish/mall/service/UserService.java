package com.deaifish.mall.service;

import com.deaifish.mall.entity.dto.SetPasswordDTO;
import com.deaifish.mall.entity.dto.SetPaymentDTO;
import com.deaifish.mall.entity.dto.UserDTO;
import com.deaifish.mall.entity.vo.UserBriefVO;
import com.deaifish.mall.entity.vo.UserDetailedVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/14 15:23
 */
public interface UserService {
    /**
     * 获取所有用户信息
     * @return
     */
    List<UserBriefVO> getAll();

    /**
     * 根据id获取用户详细信息
     * @param id
     * @return
     */
    UserDetailedVO getById(Long id);

    /**
     * 设置用户密码
     * @param passwordDTO
     */
    void setPassword(SetPasswordDTO passwordDTO);

    /**
     * 设置用户支付密码
     * @param paymentDTO
     */
    void setPaymentPassword(SetPaymentDTO paymentDTO);

    /**
     * 注册用户
     * @param userDTO
     */
    UserDetailedVO signUp(UserDTO userDTO);

    /**
     * 判断用户是否存在
     * @param wxId
     * @return
     */
    Boolean existsById(String wxId);

    /**
     * 更新用户信息
     * @param userDTO
     */
    void update(UserDTO userDTO);

}
