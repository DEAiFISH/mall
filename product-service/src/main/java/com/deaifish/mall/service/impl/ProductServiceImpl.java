package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.api.SearchServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.repository.ProductRepository;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:57
 */
@Slf4j
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
    @Resource
    private SearchServiceApi searchServiceApi;

    private static final QProductPO PRODUCT_PO = QProductPO.productPO;
    private static final QBrandPO BRAND_PO = QBrandPO.brandPO;
    private static final QClassifyPO CLASSIFY_PO = QClassifyPO.classifyPO;
    private static final QStockPO STOCK_PO = QStockPO.stockPO;

    @Override
    public List<ProductBriefVO> list() {
        List<ProductPO> pos = jpaQueryFactory.selectFrom(PRODUCT_PO).fetch();

        return pos.stream().map(po -> productPo2Vo(po, ProductBriefVO.class)).toList();
    }

    @Override
    public ProductVO detail(Long productId) {
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();

        return productPo2Vo(po, ProductVO.class);
    }


    @Transactional
    @Override
    public ProductVO add(ProductDTO productdto) {
        ProductPO po = productRepository.save(BeanUtil.toBean(productdto, ProductPO.class));

        // 保存商品到es中
        sync2Es(List.of(po));

        return productPo2Vo(po, ProductVO.class);
    }

    @Override
    public List<ProductVO> addBatch(List<ProductDTO> list) {
        List<ProductPO> poList = productRepository.saveAll(list.stream().map(dto -> BeanUtil.toBean(dto, ProductPO.class)).toList());

        sync2Es(poList);

        return poList.stream().map(po -> productPo2Vo(po, ProductVO.class)).toList();
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

        sync2Es(List.of(po));

        return productPo2Vo(po, ProductVO.class);
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

        searchServiceApi.deleteProductById(productId);
    }

    private <T> T productPo2Vo(ProductPO source, Class<T> clazz) {
        T vo = BeanUtil.toBean(source, clazz);

        ClassifyPO classifyPo = jpaQueryFactory.selectFrom(CLASSIFY_PO).where(CLASSIFY_PO.classifyId.eq(source.getClassifyId())).fetchOne();
        if (classifyPo != null) {
            ReflectUtil.setFieldValue(vo, "classifyName", classifyPo.getName());
        }
        BrandPO brandPo = jpaQueryFactory.selectFrom(BRAND_PO).where(BRAND_PO.brandId.eq(source.getBrandId())).fetchOne();
        if (brandPo != null) {
            ReflectUtil.setFieldValue(vo, "brandName", brandPo.getName());
        }
        StockPO stockPo = jpaQueryFactory.selectFrom(STOCK_PO).where(STOCK_PO.productId.eq(source.getProductId())).fetchOne();
        if (stockPo != null) {
            ReflectUtil.setFieldValue(vo, "stock", stockPo.getAmount());
        }

        return vo;
    }

    private Boolean sync2Es(List<ProductPO> list) {
        if (list.isEmpty()) {
            return false;
        }

        List<ProductESDTO> esdtos = list.stream().map(po -> {
            ProductVO vo = productPo2Vo(po, ProductVO.class);
            return BeanUtil.toBean(vo, ProductESDTO.class);
        }).toList();

        R<Boolean> booleanR = searchServiceApi.saveProductBatch(esdtos);
        log.info("保存商品到es中:{}", booleanR.getData());

        return booleanR.getData();
    }
}
