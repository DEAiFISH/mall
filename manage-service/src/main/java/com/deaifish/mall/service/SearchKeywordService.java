package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.SearchKeywordDTO;
import com.deaifish.mall.pojo.vo.SearchKeywordVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:04
 */
public interface SearchKeywordService {
    /**
     * 查询搜索关键词列表
     * @return
     */
    List<SearchKeywordVO> list();

    /**
     * 添加搜索关键词
     * @param searchKeywordDTO
     * @return
     */
    SearchKeywordVO add(SearchKeywordDTO searchKeywordDTO);

    /**
     * 更新搜索关键词
     * @param searchKeywordDTO
     * @return
     */
    SearchKeywordVO update(SearchKeywordDTO searchKeywordDTO);

    /**
     * 更新搜索关键词的访问量
     * @param id
     * @return
     */
    SearchKeywordVO updateAmount(Long id);

    /**
     * 删除搜索关键词
     * @param id
     */
    void delete(Long id);

}