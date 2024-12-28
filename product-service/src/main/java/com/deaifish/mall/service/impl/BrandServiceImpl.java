package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.po.QBrandPO;
import com.deaifish.mall.pojo.po.BrandPO;
import com.deaifish.mall.pojo.vo.BrandVO;
import com.deaifish.mall.repository.BrandRepository;
import com.deaifish.mall.service.BrandService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 15:53
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private BrandRepository brandRepository;

    private static final QBrandPO BRAND_PO = QBrandPO.brandPO;


    @Override
    public List<BrandVO> list() {
        List<BrandPO> list = jpaQueryFactory.selectFrom(BRAND_PO).fetch();
        return list.stream().map(po -> BeanUtil.toBean(po, BrandVO.class)).toList();
    }

    @Override
    @Transactional
    public BrandVO add(BrandVO brandVO) {
        BrandPO brandPO = BeanUtil.toBean(brandVO, BrandPO.class);
        brandPO = brandRepository.save(brandPO);
        return BeanUtil.toBean(brandPO, BrandVO.class);
    }

    @Override
    @Transactional
    public BrandVO update(BrandVO brandVO) {
        jpaQueryFactory.update(BRAND_PO)
                .set(BRAND_PO.number, brandVO.getNumber())
                .set(BRAND_PO.name, brandVO.getName())
                .set(BRAND_PO.icon, brandVO.getIcon())
                .set(BRAND_PO.picture, brandVO.getPicture())
                .set(BRAND_PO.description, brandVO.getDescription())
                .where(BRAND_PO.brandId.eq(brandVO.getBrandId()))
                .execute();
        BrandPO po = jpaQueryFactory.selectFrom(BRAND_PO).where(BRAND_PO.brandId.eq(brandVO.getBrandId())).fetchOne();
        return BeanUtil.toBean(po, BrandVO.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jpaQueryFactory.delete(BRAND_PO).where(BRAND_PO.brandId.eq(id)).execute();
    }
}
