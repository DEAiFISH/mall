package com.deaifish.mall.service;

import com.deaifish.mall.entity.dto.LabelDTO;
import com.deaifish.mall.entity.vo.LabelVO;
import com.deaifish.mall.entity.vo.UserInterestVO;

import javax.validation.Valid;
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

    /**
     * 更新用户兴趣度
     * @param ids
     */
    void interestUpdate(List<Integer> ids, Long userID);

    /**
     * 查询用户兴趣度
     * @param userID
     * @return
     */
    List<UserInterestVO> interestList(Long userID);

    /**
     * 删除用户兴趣度
     * @param userID
     */
    void interestDelete(Long userID);
}
