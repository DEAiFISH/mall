package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.BrandDTO;
import com.deaifish.mall.pojo.po.BrandPO;
import com.deaifish.mall.pojo.po.QBrandPO;
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
    public BrandVO add(BrandDTO brandDTO) {
        BrandPO brandPO = BeanUtil.toBean(brandDTO, BrandPO.class);
        brandPO = brandRepository.save(brandPO);
        return BeanUtil.toBean(brandPO, BrandVO.class);
    }

    @Override
    @Transactional
    public BrandVO update(BrandDTO brandDTO) {
        jpaQueryFactory.update(BRAND_PO)
                .set(BRAND_PO.number, brandDTO.getNumber())
                .set(BRAND_PO.name, brandDTO.getName())
                .set(BRAND_PO.icon, brandDTO.getIcon())
                .set(BRAND_PO.picture, brandDTO.getPicture())
                .set(BRAND_PO.description, brandDTO.getDescription())
                .where(BRAND_PO.brandId.eq(brandDTO.getBrandId()))
                .execute();
        BrandPO po = jpaQueryFactory.selectFrom(BRAND_PO).where(BRAND_PO.brandId.eq(brandDTO.getBrandId())).fetchOne();
        return BeanUtil.toBean(po, BrandVO.class);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jpaQueryFactory.delete(BRAND_PO).where(BRAND_PO.brandId.eq(id)).execute();
    }
}
