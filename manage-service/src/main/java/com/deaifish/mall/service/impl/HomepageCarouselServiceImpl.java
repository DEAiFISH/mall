package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.HomepageCarouselDTO;
import com.deaifish.mall.pojo.po.HomepageCarouselPO;
import com.deaifish.mall.pojo.po.QHomepageCarouselPO;
import com.deaifish.mall.pojo.vo.HomepageCarouselVO;
import com.deaifish.mall.repository.HomepageCarouseRepository;
import com.deaifish.mall.service.HomepageCarouselService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
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
public class HomepageCarouselServiceImpl implements HomepageCarouselService {

    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private HomepageCarouseRepository homepageCarouseRepository;

    private static final QHomepageCarouselPO HOMEPAGE_CAROUSEL_PO = QHomepageCarouselPO.homepageCarouselPO;


    @Override
    public List<HomepageCarouselVO> list() {
        List<HomepageCarouselPO> fetch = jpaQueryFactory.selectFrom(HOMEPAGE_CAROUSEL_PO).fetch();

        return fetch.stream().map(po -> BeanUtil.toBean(po, HomepageCarouselVO.class)).toList();
    }

    @Override
    @Transactional
    public HomepageCarouselVO add(HomepageCarouselDTO homepageCarouselDTO) {
        HomepageCarouselPO po = BeanUtil.toBean(homepageCarouselDTO, HomepageCarouselPO.class);
        return BeanUtil.toBean(homepageCarouseRepository.save(po), HomepageCarouselVO.class);
    }

    @Override
    @Transactional
    public HomepageCarouselVO update(HomepageCarouselDTO homepageCarouselDTO) {
        jpaQueryFactory.update(HOMEPAGE_CAROUSEL_PO)
                .set(HOMEPAGE_CAROUSEL_PO.productId, homepageCarouselDTO.getProductId())
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
}