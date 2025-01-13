package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.SearchKeywordDTO;
import com.deaifish.mall.pojo.po.QSearchKeywordPO;
import com.deaifish.mall.pojo.po.SearchKeywordPO;
import com.deaifish.mall.pojo.vo.SearchKeywordVO;
import com.deaifish.mall.repository.SearchKeywordRepository;
import com.deaifish.mall.service.SearchKeywordService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:04
 */
@Service
@RequiredArgsConstructor
public class SearchKeywordServiceImpl implements SearchKeywordService {

    private final JPAQueryFactory jpaQueryFactory;
    private final SearchKeywordRepository searchKeywordRepository;

    private static final QSearchKeywordPO SEARCH_KEYWORD_PO = QSearchKeywordPO.searchKeywordPO;


    @Override
    public List<SearchKeywordVO> list() {
        List<SearchKeywordPO> pos = jpaQueryFactory.selectFrom(SEARCH_KEYWORD_PO).fetch();

        return pos.stream().map(po -> BeanUtil.toBean(po, SearchKeywordVO.class)).toList();
    }

    @Override
    @Transactional
    public SearchKeywordVO add(SearchKeywordDTO searchKeywordDTO) {
        SearchKeywordPO po = BeanUtil.toBean(searchKeywordDTO, SearchKeywordPO.class);
        return BeanUtil.toBean(searchKeywordRepository.save(po), SearchKeywordVO.class);
    }

    @Override
    @Transactional
    public SearchKeywordVO update(SearchKeywordDTO searchKeywordDTO) {
        jpaQueryFactory.update(SEARCH_KEYWORD_PO)
                .set(SEARCH_KEYWORD_PO.content, searchKeywordDTO.getContent())
                .set(SEARCH_KEYWORD_PO.amount, searchKeywordDTO.getAmount())
                .where(SEARCH_KEYWORD_PO.searchKeywordId.eq(searchKeywordDTO.getSearchKeywordId())).execute();

        SearchKeywordPO po = jpaQueryFactory.selectFrom(SEARCH_KEYWORD_PO).where(SEARCH_KEYWORD_PO.searchKeywordId.eq(searchKeywordDTO.getSearchKeywordId())).fetchOne();
        return BeanUtil.toBean(searchKeywordRepository.save(po), SearchKeywordVO.class);
    }

    @Override
    @Transactional
    public SearchKeywordVO updateAmount(Long id) {
        SearchKeywordPO po = jpaQueryFactory.select(SEARCH_KEYWORD_PO).from(SEARCH_KEYWORD_PO).where(SEARCH_KEYWORD_PO.searchKeywordId.eq(id)).fetchOne();
        if (po == null) {
            throw new MallException("关键词不存在");
        }
        po.setAmount(po.getAmount() + 1);
        jpaQueryFactory.update(SEARCH_KEYWORD_PO)
                .set(SEARCH_KEYWORD_PO.amount, po.getAmount())
                .where(SEARCH_KEYWORD_PO.searchKeywordId.eq(id)).execute();
        return BeanUtil.toBean(po, SearchKeywordVO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jpaQueryFactory.delete(SEARCH_KEYWORD_PO).where(SEARCH_KEYWORD_PO.searchKeywordId.eq(id)).execute();
    }
}