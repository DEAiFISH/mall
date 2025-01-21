package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ResetPasswordDTO;
import com.deaifish.mall.pojo.dto.SetPaymentDTO;
import com.deaifish.mall.pojo.dto.UserDTO;
import com.deaifish.mall.pojo.qo.UserQO;
import com.deaifish.mall.pojo.vo.UserBriefVO;
import com.deaifish.mall.pojo.vo.UserDetailedVO;

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
    List<UserBriefVO> getAll(UserQO qo);

    /**
     * 根据id获取用户详细信息
     * @param id
     * @return
     */
    UserDetailedVO getById(Long id);

    /**
     * 设置用户头像
     * @param id
     * @param avatar
     */
    void setAvatar(Long id, String avatar);

    /**
     * 设置用户密码
     * @param resetPasswordDTO
     */
    void setPassword(ResetPasswordDTO resetPasswordDTO);

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
    UserDetailedVO update(UserDTO userDTO);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

}
