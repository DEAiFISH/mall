package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.VoucherDTO;
import com.deaifish.mall.pojo.vo.VoucherVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:00
 */
public interface VoucherService {
    /**
     * 查询优惠卷列表
     * @param name
     * @return
     */
    List<VoucherVO> list(String name);

    /**
     * 添加优惠卷
     * @param voucherDTO
     * @return
     */
    VoucherVO add(VoucherDTO voucherDTO);

    /**
     * 更新优惠卷
     * @param voucherDTO
     * @return
     */
    VoucherVO update(VoucherDTO voucherDTO);

    /**
     * 领取优惠卷
     * @param voucherId
     * @return
     */
    VoucherVO receive(Long voucherId);

    /**
     * 删除优惠卷
     * @param id
     */
    void delete(Long id);


}