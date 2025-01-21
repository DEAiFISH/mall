package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.BrandDTO;
import com.deaifish.mall.pojo.po.BrandPO;
import com.deaifish.mall.pojo.po.QBrandPO;
import com.deaifish.mall.pojo.qo.BrandQO;
import com.deaifish.mall.pojo.vo.BrandVO;
import com.deaifish.mall.repository.BrandRepository;
import com.deaifish.mall.service.BrandService;
import com.querydsl.core.types.Predicate;
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
 * @date 2024/12/28 15:53
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final BrandRepository brandRepository;
    private final BIZServiceApi bizServiceApi;
    private final PathProperties pathProperties;

    private static final QBrandPO BRAND_PO = QBrandPO.brandPO;


    @Override
    public List<BrandVO> list( BrandQO qo) {
        List<BrandPO> list = jpaQueryFactory.selectFrom(BRAND_PO)
                .where(createParam(qo)).fetch();
        return list.stream().map(po -> BeanUtil.toBean(po, BrandVO.class)).toList();
    }

    @Override
    @Transactional
    public BrandVO add(BrandDTO brandDTO) {
        BrandPO brandPO = BeanUtil.toBean(brandDTO, BrandPO.class);
        brandPO = brandRepository.save(brandPO);
        entityManager.refresh(brandPO);
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
        BrandPO po = jpaQueryFactory.selectFrom(BRAND_PO).where(BRAND_PO.brandId.eq(id)).fetchOne();
        if (po == null) {
            throw new MallException("商品不存在");
        }
        bizServiceApi.delete(po.getIcon());
        bizServiceApi.delete(po.getPicture());
        jpaQueryFactory.delete(BRAND_PO).where(BRAND_PO.brandId.eq(id)).execute();
    }

    private Predicate[] createParam(BrandQO qo){
        List<Predicate> param = new ArrayList<>();
        if (StrUtil.isNotBlank(qo.getNumber())) {
            param.add(BRAND_PO.number.like("%" + qo.getNumber() + "%"));
        }
        if (StrUtil.isNotBlank(qo.getName())) {
            param.add(BRAND_PO.name.like("%" + qo.getName() + "%"));
        }
        return param.toArray(new Predicate[0]);
    }
}
