package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.ProductServiceApi;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.po.ProductCollectPO;
import com.deaifish.mall.pojo.po.ProductPO;
import com.deaifish.mall.pojo.po.QProductCollectPO;
import com.deaifish.mall.pojo.po.QProductPO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductCollectVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.repository.ProductCollectRepository;
import com.deaifish.mall.service.LabelService;
import com.deaifish.mall.service.ProductCollectService;
import com.deaifish.mall.service.UserService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.deaifish.mall.pojo.po.QProductPO.productPO;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/24 14:14
 */
@Service
@RequiredArgsConstructor
public class ProductCollectServiceImpl implements ProductCollectService {
    private final ProductCollectRepository productCollectRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final ProductServiceApi productServiceApi;
    private final LabelService labelService;

    private static final QProductCollectPO PRODUCT_COLLECT_PO = QProductCollectPO.productCollectPO;
    private static final QProductPO PRODUCT_PO = productPO;


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
    public Long count(Long uId) {
        return jpaQueryFactory.select(PRODUCT_COLLECT_PO.collectId.count()).from(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(uId)).fetchOne();
    }

    @Override
    @Transactional
    public Boolean collect(Long uId, Long pId) {

        if(isExist(uId, pId)) {
            throw new MallException("已经收藏过了");
        }

        ProductVO productVO = productServiceApi.getProductById(pId).getData();
        if(productVO == null) {
            throw  new MallException("商品不存在");
        }
        ProductCollectPO po = ProductCollectPO.builder().productId(pId).userId(uId).productName(productVO.getName()).coverPicture(productVO.getCoverPicture()).build();
        productCollectRepository.save(po);

        // 更新兴趣度
        CompletableFuture.runAsync(() -> {
            List<Integer> ids = productServiceApi.listByProductId(pId).getData().stream().map(LabelVO::getLabelId).toList();
            ArrayList<Integer> list = new ArrayList<>(ids);
            list.addAll(ids);
            labelService.interestUpdate(list, uId);
        });

        return true;
    }

    @Override
    @Transactional
    public Boolean cancelCollect(Long uId, Long pId) {
        jpaQueryFactory.delete(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(uId).and(PRODUCT_COLLECT_PO.productId.eq(pId))).execute();
        return true;
    }

    private Boolean isExist(Long uId, Long pId) {
        return jpaQueryFactory.selectFrom(PRODUCT_COLLECT_PO).where(PRODUCT_COLLECT_PO.userId.eq(uId).and(PRODUCT_COLLECT_PO.productId.eq(pId))).fetchOne() != null;
    }
}