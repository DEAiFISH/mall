package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.VoucherPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherPO, Long> {
}