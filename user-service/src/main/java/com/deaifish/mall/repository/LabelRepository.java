package com.deaifish.mall.repository;

import com.deaifish.mall.entity.po.LabelPO;
import com.deaifish.mall.entity.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<LabelPO, Integer> {

}
