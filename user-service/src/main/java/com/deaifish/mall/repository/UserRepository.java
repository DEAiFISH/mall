package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:05
 */
@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
}