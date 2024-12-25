package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.ShippingAddressPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/23 19:58
 */
@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressPO, Long> {
}