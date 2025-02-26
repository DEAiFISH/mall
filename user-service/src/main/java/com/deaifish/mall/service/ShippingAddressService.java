package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ShippingAddressDTO;
import com.deaifish.mall.pojo.vo.ShippingAddressVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 22:10
 */
public interface ShippingAddressService {
    /**
     * 查询所有收货地址
     *
     * @param uId
     * @return
     */
    List<ShippingAddressVO> listAll(Long uId);

    /**
     * 查询单个收货地址
     * @param saId 收货地址ID
     * @return
     */
    ShippingAddressVO get(Long saId);

    /**
     * 添加收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    ShippingAddressVO add(ShippingAddressDTO shippingAddressDTO);

    /**
     * 更新收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    ShippingAddressVO update(ShippingAddressDTO shippingAddressDTO);

    /**
     * 删除收货地址
     *
     * @param saId
     * @return
     */
    Boolean delete(Long saId);

}