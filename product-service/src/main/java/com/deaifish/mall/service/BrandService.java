package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.BrandDTO;
import com.deaifish.mall.pojo.qo.BrandQO;
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
    List<BrandVO> list( BrandQO qo);

    /**
     * 添加品牌
     * @param brandDTO
     * @return
     */
    BrandVO add(BrandDTO brandDTO);

    /**
     * 更新品牌
     * @param brandDTO
     * @return
     */
    BrandVO update(BrandDTO brandDTO);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Integer id);
}
