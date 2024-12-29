package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.pojo.po.QUserBrowseHistoryPO;
import com.deaifish.mall.pojo.po.UserBrowseHistoryPO;
import com.deaifish.mall.pojo.vo.UserBrowseHistoryVO;
import com.deaifish.mall.repository.UserBrowseHistoryRepository;
import com.deaifish.mall.service.UserBrowseHistoryService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/25 0:32
 */
@Service
public class UserBrowseHistoryServiceImpl implements UserBrowseHistoryService {
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private UserBrowseHistoryRepository userBrowseHistoryRepository;

    private static final QUserBrowseHistoryPO USER_BROWSE_HISTORY_PO = QUserBrowseHistoryPO.userBrowseHistoryPO;


    @Override
    public List<UserBrowseHistoryVO> list(Long uId) {
        List<UserBrowseHistoryPO> pos = jpaQueryFactory.select(USER_BROWSE_HISTORY_PO).from(USER_BROWSE_HISTORY_PO)
                .where(USER_BROWSE_HISTORY_PO.userId.eq(uId)).fetch();
        return pos.stream().map(po -> BeanUtil.toBean(po, UserBrowseHistoryVO.class))
                .toList();
    }

    @Override
    @Transactional
    public void add(UserBrowseHistoryDTO userBrowseHistoryDTO) {
        userBrowseHistoryRepository.save(BeanUtil.toBean(userBrowseHistoryDTO, UserBrowseHistoryPO.class));
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        jpaQueryFactory.delete(USER_BROWSE_HISTORY_PO).where(USER_BROWSE_HISTORY_PO.historyId.in(ids)).execute();
    }
}