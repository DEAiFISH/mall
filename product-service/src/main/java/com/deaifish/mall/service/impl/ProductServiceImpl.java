package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.hash.Hash;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.api.SearchServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.mall.api.UserServiceApi;
import com.deaifish.mall.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.pojo.bo.CBProfileBO;
import com.deaifish.mall.pojo.bo.Item;
import com.deaifish.mall.pojo.dto.ProductDTO;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.qo.ProductQO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductBriefVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.repository.ProductRepository;
import com.deaifish.mall.response.R;
import com.deaifish.mall.service.ProductLabelService;
import com.deaifish.mall.service.ProductService;
import com.deaifish.mall.service.StockService;
import com.deaifish.mall.util.CBUtil;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:57
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final BIZServiceApi bizServiceApi;
    private final PathProperties pathProperties;
    private final SearchServiceApi searchServiceApi;
    private final StockService stokeService;
    private final ProductLabelService productLabelService;
    private final UserServiceApi userServiceApi;

    private static final QProductPO PRODUCT_PO = QProductPO.productPO;
    private static final QBrandPO BRAND_PO = QBrandPO.brandPO;
    private static final QClassifyPO CLASSIFY_PO = QClassifyPO.classifyPO;
    private static final QStockPO STOCK_PO = QStockPO.stockPO;

    @Override
    public List<ProductBriefVO> list( ProductQO qo) {
        List<ProductPO> pos = jpaQueryFactory.selectFrom(PRODUCT_PO)
                .where(createParam(qo)).fetch();

        return pos.stream().map(po -> productPo2Vo(po, ProductBriefVO.class)).toList();
    }

    @Override
    public ProductVO detail(Long productId) {
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();

        return productPo2Vo(po, ProductVO.class);
    }

    @Override
    public List<ProductVO> listHomepage() {
        List<ProductPO> poList = jpaQueryFactory.selectFrom(PRODUCT_PO).orderBy(PRODUCT_PO.sale.desc()).limit(100).fetch();

        // 处理商品特征
        Map<Long, ProductPO> beanMap = new HashMap<>();

        List<Item> items = poList.stream().map(po -> {
            Long productId = po.getProductId();
            Map<String, Long> futures = new HashMap<>();
            Item item = new Item(productId, futures);

            productLabelService.listByProductId(productId).forEach(label -> {
                futures.put(label.getName(), label.getWeights());
            });

            beanMap.put(productId, po);
            return item;
        }).toList();


        // 处理用户特征
        Long userId = AuthUserContext.get().getUserId();
        List<UserInterestVO> data = userServiceApi.interestList(userId).getData();
        Map<String, Long> userProfileFeatures = new HashMap<>();
        if(data != null) {
            data.forEach((vo -> {
                userProfileFeatures.put(vo.getLabel(), vo.getValue());
            }));
        }

        CBProfileBO<ProductPO> cbProfileBO = new CBProfileBO<>(userProfileFeatures, items,beanMap);

        CBUtil<ProductPO> cbUtil = new CBUtil<>();
        List<ProductPO> pos = cbUtil.recommend(cbProfileBO, 20);

        return pos.stream().map(po -> productPo2Vo(po, ProductVO.class)).toList();
    }

    @Transactional
    @Override
    public ProductVO add(ProductDTO productdto) {
        ProductPO po = BeanUtil.toBean(productdto, ProductPO.class);
        productRepository.save(po);

        entityManager.refresh(po);

        stokeService.createStock(po.getProductId(), 0);

        // 保存商品到es中
        sync2Es(List.of(po));

        entityManager.refresh(po);
        return productPo2Vo(po, ProductVO.class);
    }

    @Override
    public List<ProductVO> addBatch(List<ProductDTO> list) {
        List<ProductPO> poList = list.stream().map(dto -> BeanUtil.toBean(dto, ProductPO.class)).toList();
        productRepository.saveAll(poList);

        sync2Es(poList);

        poList.forEach(entityManager::refresh);
        return poList.stream().map(po -> productPo2Vo(po, ProductVO.class)).toList();
    }

    @Override
    @Transactional
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
    public ProductVO sale(Long productId, Integer sale) {
        ProductPO po = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();
        if(po == null) {
            throw new MallException("商品不存在");
        }
        jpaQueryFactory.update(PRODUCT_PO)
                .set(PRODUCT_PO.sale, sale + po.getSale());

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
            ProductESDTO esdto = BeanUtil.toBean(vo, ProductESDTO.class);
            List<LabelVO> labelVOS = productLabelService.listByProductId(vo.getProductId());
            StringBuilder sb = new StringBuilder();
            for (LabelVO labelVO : labelVOS) {
                sb.append(labelVO.getName()).append(",");
            }
            esdto.setLabel(sb.toString());
            return esdto;
        }).toList();

        R<Boolean> booleanR = searchServiceApi.saveProductBatch(esdtos);
        log.info("保存商品到es中:{}", booleanR.getData());

        return booleanR.getData();
    }

    private Predicate[] createParam(ProductQO qo) {
        List<Predicate> predicates = new ArrayList<>();
        if (StrUtil.isNotBlank(qo.getNumber())) {
            predicates.add(PRODUCT_PO.number.like("%" + qo.getNumber() + "%"));
        }
        if (StrUtil.isNotBlank(qo.getName())) {
            predicates.add(PRODUCT_PO.name.like("%" + qo.getName() + "%"));
        }
        if(qo.getPriceUP() != null && qo.getPriceDown() != null) {
            predicates.add(PRODUCT_PO.price.between(qo.getPriceDown(), qo.getPriceUP()));
        }
        if(qo.getPreferentialPriceUP() != null && qo.getPreferentialPriceDown() != null) {
            predicates.add(PRODUCT_PO.preferentialPrice.between(qo.getPreferentialPriceDown(), qo.getPreferentialPriceUP()));
        }
        if(qo.getSaleUP() != null && qo.getSaleDown() != null) {
            predicates.add(PRODUCT_PO.sale.between(qo.getSaleDown(), qo.getSaleUP()));
        }
        return predicates.toArray(new Predicate[0]);
    }
}
