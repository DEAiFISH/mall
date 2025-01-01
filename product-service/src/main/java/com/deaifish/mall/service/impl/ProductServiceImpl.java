package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.po.ProductPO;
import com.deaifish.mall.pojo.po.QProductPO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.repository.ProductRepository;
import com.deaifish.mall.service.ProductService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:57
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private JPAQueryFactory jpaQueryFactory;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private BIZServiceApi bizServiceApi;
    @Resource
    private PathProperties pathProperties;

    private static final QProductPO PRODUCT_PO = QProductPO.productPO;

    @Override
    public List<ProductBriefVO> list() {
        return jpaQueryFactory.select(Projections.bean(ProductBriefVO.class, PRODUCT_PO.productId, PRODUCT_PO.name, PRODUCT_PO.classifyId,
                PRODUCT_PO.brandId, PRODUCT_PO.price, PRODUCT_PO.preferentialPrice, PRODUCT_PO.sale, PRODUCT_PO.briefDescription,
                PRODUCT_PO.status, PRODUCT_PO.coverPicture)).from(PRODUCT_PO).fetch();
    }

    @Override
    public ProductVO detail(Long productId) {
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();
        return BeanUtil.toBean(po, ProductVO.class);
    }

    @Transactional
    @Override
    public ProductVO add(ProductDTO productdto) {
        ProductPO save = productRepository.save(BeanUtil.toBean(productdto, ProductPO.class));
        return BeanUtil.toBean(save, ProductVO.class);
    }

    @Override
    public List<ProductVO> addBatch(List<ProductDTO> list) {
        List<ProductPO> poList = productRepository.saveAll(list.stream().map(dto -> BeanUtil.toBean(dto, ProductPO.class)).toList());
        return poList.stream().map(po -> BeanUtil.toBean(po, ProductVO.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductVO update(ProductDTO productdto) {
        jpaQueryFactory.update(PRODUCT_PO)
                .set(PRODUCT_PO.number, productdto.getNumber())
                .set(PRODUCT_PO.name, productdto.getName())
                .set(PRODUCT_PO.classifyId, productdto.getClassifyId())
                .set(PRODUCT_PO.brandId, productdto.getBrandId())
                .set(PRODUCT_PO.price, productdto.getPrice())
                .set(PRODUCT_PO.preferentialPrice, productdto.getPreferentialPrice())
                .set(PRODUCT_PO.parameter, productdto.getParameter())
                .set(PRODUCT_PO.sale, productdto.getSale())
                .set(PRODUCT_PO.briefDescription, productdto.getBriefDescription())
                .set(PRODUCT_PO.description, productdto.getDescription())
                .set(PRODUCT_PO.status, productdto.getStatus())
                .set(PRODUCT_PO.coverPicture, productdto.getCoverPicture())
                .set(PRODUCT_PO.detailsPicture, productdto.getDetailsPicture())
                .where(PRODUCT_PO.productId.eq(productdto.getProductId()))
                .execute();
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productdto.getProductId())).fetchOne();
        return BeanUtil.toBean(po, ProductVO.class);
    }

    @Override
    @Transactional
    public void delete(Long productId) {
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();
        if (po == null) {
            throw new MallException("商品不存在");
        }
        ArrayList<String> pictures = new ArrayList<>();
        pictures.add(po.getCoverPicture());
        pictures.addAll(po.getDetailsPicture());

        // 异步删除图片
        List<CompletableFuture<Void>> futures = pictures.stream().map(pic ->
                CompletableFuture.runAsync(() ->
                        bizServiceApi.delete(pathProperties.getEvaluationDirPath() + pic))).toList();
        // 等待所有图片删除完成后再执行下一步
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        jpaQueryFactory.delete(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).execute();
    }
}
