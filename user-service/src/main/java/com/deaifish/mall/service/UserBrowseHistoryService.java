package com.deaifish.mall.service;

import com.deaifish.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.pojo.vo.UserBrowseHistoryVO;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/25 0:31
 */
public interface UserBrowseHistoryService {
    /**
     * 浏览记录列表
     * @param uId
     * @return
     */
    List<UserBrowseHistoryVO> list(Long uId);

    /**
     * 添加浏览记录
     * @param userBrowseHistoryDTO
     */
    UserBrowseHistoryVO add(UserBrowseHistoryDTO userBrowseHistoryDTO);

    /**
     * 删除浏览记录
     * @param ids
     */
    Boolean delete(List<Long> ids);
}