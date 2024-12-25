package com.deaifish.mall.service;

import com.deaifish.mall.entity.dto.ShippingAddressDTO;
import com.deaifish.mall.entity.vo.ShippingAddressVO;

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
     * 添加收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    Boolean add(ShippingAddressDTO shippingAddressDTO);

    /**
     * 更新收货地址
     *
     * @param shippingAddressDTO
     * @return
     */
    Boolean update(ShippingAddressDTO shippingAddressDTO);

    /**
     * 删除收货地址
     *
     * @param saId
     * @return
     */
    Boolean delete(Long saId);

}