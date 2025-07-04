package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.LabelDTO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.UserInterestVO;

import java.util.List;

public interface LabelService {
    /**
     * 查询所有标签
     * @param name
     * @return
     */
    List<LabelVO> list(String name);

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
    void interestUpdate(List<Integer> ids, Long userID, Integer number);

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
