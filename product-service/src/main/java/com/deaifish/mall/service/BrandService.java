package com.deaifish.mall.service;

import com.deaifish.mall.pojo.vo.BrandVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 15:53
 */
public interface BrandService {
    /**
     * 查询品牌列表
     * @return
     */
    List<BrandVO> list();

    /**
     * 添加品牌
     * @param brandVO
     * @return
     */
    BrandVO add(BrandVO brandVO);

    /**
     * 更新品牌
     * @param brandVO
     * @return
     */
    BrandVO update(BrandVO brandVO);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Integer id);
}
