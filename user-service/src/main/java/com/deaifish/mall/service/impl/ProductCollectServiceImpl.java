package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.po.ProductCollectPO;
import com.deaifish.mall.po.QProductCollectPO;
import com.deaifish.mall.po.QProductPO;
import com.deaifish.mall.pojo.vo.ProductCollectVO;
import com.deaifish.mall.repository.ProductCollectRepository;
import com.deaifish.mall.service.ProductCollectService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:14
 */
@Service
public class ProductCollectServiceImpl implements ProductCollectService {
    @Resource
    private ProductCollectRepository productCollectRepository;
    @Resource
    private JPAQueryFactory jpaQueryFactory;

    private static final QProductCollectPO PRODUCT_COLLECT_PO = QProductCollectPO.productCollectPO;
    private static final QProductPO PRODUCT_PO = QProductPO.productPO;


    @Override
    public List<ProductCollectVO> list(Long userId) {
        List<ProductCollectPO> poList = jpaQueryFactory.select(PRODUCT_COLLECT_PO).from(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(userId)).fetch();
        return poList.stream().map((po -> BeanUtil.toBean(po, ProductCollectVO.class))).toList();
    }

    @Override
    public Boolean isCollect(Long uId, Long pId) {
        ProductCollectPO po = jpaQueryFactory.selectFrom(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(uId).and(PRODUCT_COLLECT_PO.productId.eq(pId))).fetchOne();
        return po != null;
    }

    @Override
    @Transactional
    public Boolean collect(Long uId, Long pId) {
        String coverPicture = jpaQueryFactory.select(PRODUCT_PO.coverPicture).from(PRODUCT_PO).where(PRODUCT_PO.productId.eq(pId)).fetchOne();
        ProductCollectPO po = ProductCollectPO.builder().productId(pId).userId(uId).picture(coverPicture).build();
        productCollectRepository.save(po);
        return true;
    }

    @Override
    @Transactional
    public Boolean cancelCollect(Long uId, Long pId) {
        jpaQueryFactory.delete(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(uId).and(PRODUCT_COLLECT_PO.productId.eq(pId))).execute();
        return true;
    }
}