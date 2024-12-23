package com.deaifish.mall.service;

import com.deaifish.mall.entity.dto.LabelDTO;
import com.deaifish.mall.entity.vo.LabelVO;

import java.util.List;

public interface LabelService {
    /**
     * 查询所有标签
     * @return
     */
    List<LabelVO> list();

    /**
     * 添加标签
     * @param labelDTO
     * @return
     */
    LabelVO add(LabelDTO labelDTO);

    /**
     * 更新标签
     * @param labelDTO
     * @return
     */
    LabelVO update(LabelDTO labelDTO);

    /**
     * 删除标签
     * @param labelId
     */
    void delete(Integer labelId);
}
