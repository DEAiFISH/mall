package com.deaifish.mall.repository;

import com.deaifish.mall.pojo.po.SearchKeywordPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchKeywordRepository extends JpaRepository<SearchKeywordPO, Long> {
}