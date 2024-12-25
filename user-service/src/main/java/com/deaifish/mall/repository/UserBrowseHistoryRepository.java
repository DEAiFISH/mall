package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.UserBrowseHistoryPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/23 19:59
 */
@Repository
public interface UserBrowseHistoryRepository extends JpaRepository<UserBrowseHistoryPO, Long> {
}