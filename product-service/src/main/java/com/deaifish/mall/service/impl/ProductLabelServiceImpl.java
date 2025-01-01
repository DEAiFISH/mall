package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ProductLabelDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductLabelVO;
import com.deaifish.mall.repository.ProductLabelRepository;
import com.deaifish.mall.service.ProductLabelService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:40
 */
@Service
public class ProductLabelServiceImpl implements ProductLabelService {
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private ProductLabelRepository productLabelRepository;

    private static final QProductLabelPO PRODUCT_LABEL_PO = QProductLabelPO.productLabelPO;
    private static final QProductPO PRODUCT_PO = QProductPO.productPO;
    private static final QLabelPO LABEL_PO = QLabelPO.labelPO;


    @Override
    public List<ProductBriefVO> listByLabelId(Integer lId) {
        List<Long> ids = jpaQueryFactory.select(PRODUCT_LABEL_PO.productId).from(PRODUCT_LABEL_PO).where(PRODUCT_LABEL_PO.labelId.eq(lId)).fetch();
        List<ProductPO> productPOList = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.in(ids)).fetch();

        return productPOList.stream().map(productPO -> BeanUtil.toBean(productPO, ProductBriefVO.class)).toList();
    }

    @Override
    public List<LabelVO> listByProductId(Long pId) {
        List<Integer> ids = jpaQueryFactory.select(PRODUCT_LABEL_PO.labelId).from(PRODUCT_LABEL_PO).where(PRODUCT_LABEL_PO.productId.eq(pId)).fetch();
        List<LabelPO> labelPOList = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).where(LABEL_PO.labelId.in(ids)).fetch();

        return labelPOList.stream().map(labelPO -> BeanUtil.toBean(labelPO, LabelVO.class)).toList();
    }

    @Override
    @Transactional
    public ProductLabelVO add(ProductLabelDTO productLabelDTO) {
        ProductLabelPO po = productLabelRepository.save(BeanUtil.toBean(productLabelDTO, ProductLabelPO.class));

        return getProductLabelVO(po);
    }

    @Override
    @Transactional
    public ProductLabelVO update(ProductLabelDTO productLabelDTO) {
        jpaQueryFactory.update(PRODUCT_LABEL_PO)
                .set(PRODUCT_LABEL_PO.productId, productLabelDTO.getProductId())
                .set(PRODUCT_LABEL_PO.labelId, productLabelDTO.getLabelId())
                .where(PRODUCT_LABEL_PO.productId.eq(productLabelDTO.getProductId()))
                .execute();

        ProductLabelPO po = jpaQueryFactory.selectFrom(PRODUCT_LABEL_PO).where(PRODUCT_LABEL_PO.productLabelId.eq(productLabelDTO.getProductLabelId())).fetchOne();
        if(po == null) {
            throw new MallException("更新失败，找不到对应的产品标签记录");
        }

        return getProductLabelVO(po);
    }

    @Override
    @Transactional
    public void delete(Long plId) {
        jpaQueryFactory.delete(PRODUCT_LABEL_PO).where(PRODUCT_LABEL_PO.productLabelId.eq(plId)).execute();
    }

    private ProductLabelVO getProductLabelVO(ProductLabelPO po) {
        String productName = jpaQueryFactory.select(PRODUCT_PO.name).from(PRODUCT_PO).where(PRODUCT_PO.productId.eq(po.getProductId())).fetchOne();
        String labelName = jpaQueryFactory.select(LABEL_PO.name).from(LABEL_PO).where(LABEL_PO.labelId.eq(po.getLabelId())).fetchOne();
        ProductLabelVO vo = BeanUtil.toBean(po, ProductLabelVO.class);
        vo.setProductName(productName);
        vo.setLabelName(labelName);
        return vo;
    }
}
