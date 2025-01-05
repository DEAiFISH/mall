package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.HomepageCarouselDTO;
import com.deaifish.mall.pojo.vo.HomepageCarouselVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:03
 */
public interface HomepageCarouselService {
    /**
     * 查询轮播图列表
     * @return
     */
    List<HomepageCarouselVO> list();

    /**
     * 添加轮播图
     * @param homepageCarouselDTO
     * @return
     */
    HomepageCarouselVO add(HomepageCarouselDTO homepageCarouselDTO);

    /**
     * 更新轮播图
     * @param homepageCarouselDTO
     * @return
     */
    HomepageCarouselVO update(HomepageCarouselDTO homepageCarouselDTO);

    /**
     * 删除轮播图
     * @param id
     */
    void delete(Long id);
}