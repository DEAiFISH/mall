package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.pojo.po.QUserBrowseHistoryPO;
import com.deaifish.mall.pojo.po.UserBrowseHistoryPO;
import com.deaifish.mall.pojo.vo.UserBrowseHistoryVO;
import com.deaifish.mall.repository.UserBrowseHistoryRepository;
import com.deaifish.mall.service.UserBrowseHistoryService;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
                .where(USER_BROWSE_HISTORY_PO.userId.eq(uId))
                .orderBy(USER_BROWSE_HISTORY_PO.updateTime.desc()).fetch();
        return pos.stream().map(po -> BeanUtil.toBean(po, UserBrowseHistoryVO.class))
                .toList();
    }

    @Override
    public Long count(Long uId) {
        return jpaQueryFactory.select(USER_BROWSE_HISTORY_PO.count()).from(USER_BROWSE_HISTORY_PO).where(USER_BROWSE_HISTORY_PO.userId.eq(uId))
                .fetchOne();
    }

    @Override
    public Long allCount() {
        return jpaQueryFactory.select(USER_BROWSE_HISTORY_PO.count()).from(USER_BROWSE_HISTORY_PO).fetchOne();
    }

    @Override
    public List<Long> listYear(String year) {
        StringTemplate template = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", USER_BROWSE_HISTORY_PO.createTime, year + "-%m");
        List<Long> list = jpaQueryFactory.select(USER_BROWSE_HISTORY_PO.historyId.count()).from(USER_BROWSE_HISTORY_PO).groupBy(template).fetch();
        for (int i = list.size(); i < 12; i++) {
            list.add(0L);
        }
        return list;
    }

    @Override
    @Transactional
    public UserBrowseHistoryVO add(UserBrowseHistoryDTO userBrowseHistoryDTO) {
        // 先查询是否存在，存在则更新时间，不存在则新增
        UserBrowseHistoryPO po = jpaQueryFactory.select(USER_BROWSE_HISTORY_PO).from(USER_BROWSE_HISTORY_PO)
                .where(USER_BROWSE_HISTORY_PO.userId.eq(userBrowseHistoryDTO.getUserId())
                        .and(USER_BROWSE_HISTORY_PO.productId.eq(userBrowseHistoryDTO.getProductId())))
                .fetchOne();

        if(po ==  null){
            po = BeanUtil.toBean(userBrowseHistoryDTO, UserBrowseHistoryPO.class);
        }

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