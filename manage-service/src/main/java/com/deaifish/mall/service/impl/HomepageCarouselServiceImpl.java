package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.pojo.dto.HomepageCarouselDTO;
import com.deaifish.mall.pojo.po.HomepageCarouselPO;
import com.deaifish.mall.pojo.po.QHomepageCarouselPO;
import com.deaifish.mall.pojo.po.QProductPO;
import com.deaifish.mall.pojo.vo.HomepageCarouselVO;
import com.deaifish.mall.repository.HomepageCarouseRepository;
import com.deaifish.mall.service.HomepageCarouselService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:04
 */
@Service
@RequiredArgsConstructor
public class HomepageCarouselServiceImpl implements HomepageCarouselService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final HomepageCarouseRepository homepageCarouseRepository;

    private static final QHomepageCarouselPO HOMEPAGE_CAROUSEL_PO = QHomepageCarouselPO.homepageCarouselPO;
    private static final QProductPO PRODUCT_PO = QProductPO.productPO;


    @Override
    public List<HomepageCarouselVO> list(String productName) {
        List<HomepageCarouselPO> fetch = jpaQueryFactory.selectFrom(HOMEPAGE_CAROUSEL_PO)
                .where(createParam(productName)).fetch();

        return fetch.stream().map(po -> BeanUtil.toBean(po, HomepageCarouselVO.class)).toList();
    }

    @Override
    @Transactional
    public HomepageCarouselVO add(HomepageCarouselDTO homepageCarouselDTO) {
        HomepageCarouselPO po = BeanUtil.toBean(homepageCarouselDTO, HomepageCarouselPO.class);
        homepageCarouseRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, HomepageCarouselVO.class);
    }

    @Override
    @Transactional
    public HomepageCarouselVO update(HomepageCarouselDTO homepageCarouselDTO) {
        jpaQueryFactory.update(HOMEPAGE_CAROUSEL_PO)
                .set(HOMEPAGE_CAROUSEL_PO.productId, homepageCarouselDTO.getProductId())
                .set(HOMEPAGE_CAROUSEL_PO.productName, homepageCarouselDTO.getProductName())
                .set(HOMEPAGE_CAROUSEL_PO.status, homepageCarouselDTO.getStatus())
                .set(HOMEPAGE_CAROUSEL_PO.picture, homepageCarouselDTO.getPicture())
                .where(HOMEPAGE_CAROUSEL_PO.chartId.eq(homepageCarouselDTO.getChartId()))
                .execute();
        HomepageCarouselPO po = jpaQueryFactory.selectFrom(HOMEPAGE_CAROUSEL_PO).where(HOMEPAGE_CAROUSEL_PO.chartId.eq(homepageCarouselDTO.getChartId())).fetchOne();
        return BeanUtil.toBean(po, HomepageCarouselVO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jpaQueryFactory.delete(HOMEPAGE_CAROUSEL_PO).where(HOMEPAGE_CAROUSEL_PO.chartId.eq(id)).execute();
    }

    private Predicate[] createParam(String productName) {
        List<Predicate> param = new ArrayList<>();

        if(StrUtil.isNotBlank(productName)){
            param.add(PRODUCT_PO.name.like("%"+productName+"%"));
        }

        return param.toArray(new Predicate[0]);
    }
}