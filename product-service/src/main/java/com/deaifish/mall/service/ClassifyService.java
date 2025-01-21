package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.ClassifyDTO;
import com.deaifish.mall.pojo.vo.ClassifyVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 17:17
 */
public interface ClassifyService {
    /**
     * 查询分类列表
     * @return
     */
    List<ClassifyVO> list(String name);

    /**
     * 添加分类
     * @param classifyDTO
     * @return
     */
    ClassifyVO add(ClassifyDTO classifyDTO);

    /**
     * 更新分类
     * @param classifyDTO
     * @return
     */
    ClassifyVO update(ClassifyDTO classifyDTO);

    /**
     * 删除分类
     * @param classifyId
     * @return
     */
    void delete(Integer classifyId);
}
