package com.deaifish.mall.repository;

import com.deaifish.mall.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 16:16
 */
public interface UserRepository extends JpaRepository<UserPO, Long> {
}
