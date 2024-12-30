package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.oss.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ProductEvaluationDTO;
import com.deaifish.mall.pojo.po.ProductEvaluationPO;
import com.deaifish.mall.pojo.po.QProductEvaluationPO;
import com.deaifish.mall.pojo.vo.ProductEvaluationVO;
import com.deaifish.mall.repository.ProductEvaluationRepository;
import com.deaifish.mall.service.ProductEvaluationService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 20:17
 */
@Service
public class ProductEvaluationServiceImpl implements ProductEvaluationService {

    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private ProductEvaluationRepository productEvaluationRepository;
    @Resource
    private BIZServiceApi bizServiceApi;
    @Resource
    private PathProperties pathProperties;

    private static final QProductEvaluationPO PRODUCT_EVALUATION_PO = QProductEvaluationPO.productEvaluationPO;

    @Override
    public List<ProductEvaluationVO> listByProductId(Long pId) {
        List<ProductEvaluationPO> poList = jpaQueryFactory.selectFrom(PRODUCT_EVALUATION_PO).where(PRODUCT_EVALUATION_PO.productId.eq(pId)).fetch();
        return poList.stream().map(productEvaluationPO -> BeanUtil.toBean(productEvaluationPO, ProductEvaluationVO.class)).toList();
    }

    @Override
    @Transactional
    public ProductEvaluationVO add(ProductEvaluationDTO productEvaluationDTO) {
        ProductEvaluationPO po = productEvaluationRepository.save(BeanUtil.toBean(productEvaluationDTO, ProductEvaluationPO.class));
        return BeanUtil.toBean(po, ProductEvaluationVO.class);
    }

    @Override
    @Transactional
    public ProductEvaluationVO update(ProductEvaluationDTO productEvaluationDTO) {
        jpaQueryFactory.update(PRODUCT_EVALUATION_PO)
                .set(PRODUCT_EVALUATION_PO.productId, productEvaluationDTO.getProductId())
                .set(PRODUCT_EVALUATION_PO.userId, productEvaluationDTO.getUserId())
                .set(PRODUCT_EVALUATION_PO.content, productEvaluationDTO.getContent())
                .set(PRODUCT_EVALUATION_PO.reply, productEvaluationDTO.getReply())
                .set(PRODUCT_EVALUATION_PO.isReply, productEvaluationDTO.getIsReply())
                .set(PRODUCT_EVALUATION_PO.star, productEvaluationDTO.getStar())
                .set(PRODUCT_EVALUATION_PO.picture, productEvaluationDTO.getPicture())
                .set(PRODUCT_EVALUATION_PO.isAnonymous, productEvaluationDTO.getIsAnonymous())
                .where(PRODUCT_EVALUATION_PO.evaluationId.eq(productEvaluationDTO.getEvaluationId())).execute();

        ProductEvaluationPO po = jpaQueryFactory.selectFrom(PRODUCT_EVALUATION_PO).where(PRODUCT_EVALUATION_PO.evaluationId.eq(productEvaluationDTO.getEvaluationId())).fetchOne();
        return BeanUtil.toBean(po, ProductEvaluationVO.class);
    }

    @Override
    @Transactional
    public void delete(Long evaluationId) {
        ProductEvaluationPO po = jpaQueryFactory.selectFrom(PRODUCT_EVALUATION_PO).where(PRODUCT_EVALUATION_PO.evaluationId.eq(evaluationId)).fetchOne();
        if (po == null) {
            throw new MallException("商品不存在");
        }
        // 异步删除图片
        List<CompletableFuture<Void>> futures = po.getPicture().stream().map(pic ->
                CompletableFuture.runAsync(() ->
                        bizServiceApi.delete(pathProperties.getEvaluationDirPath() + pic))).toList();
        // 等待所有图片删除完成后再执行下一步
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        jpaQueryFactory.delete(PRODUCT_EVALUATION_PO).where(PRODUCT_EVALUATION_PO.evaluationId.eq(evaluationId)).execute();
    }
}
