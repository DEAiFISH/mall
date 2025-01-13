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
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserBrowseHistoryServiceImpl implements UserBrowseHistoryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final UserBrowseHistoryRepository userBrowseHistoryRepository;

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
    public UserBrowseHistoryVO add(UserBrowseHistoryDTO userBrowseHistoryDTO) {
        UserBrowseHistoryPO po = BeanUtil.toBean(userBrowseHistoryDTO, UserBrowseHistoryPO.class);
        userBrowseHistoryRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, UserBrowseHistoryVO.class);
    }

    @Override
    @Transactional
    public Boolean delete(List<Long> ids) {
        jpaQueryFactory.delete(USER_BROWSE_HISTORY_PO).where(USER_BROWSE_HISTORY_PO.historyId.in(ids)).execute();
        return true;
    }
}