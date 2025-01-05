package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.HomepageCarouselPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomepageCarouseRepository extends JpaRepository<HomepageCarouselPO, Long> {
}