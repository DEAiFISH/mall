package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.po.QStockPO;
import com.deaifish.mall.pojo.po.StockPO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.repository.StockRepository;
import com.deaifish.mall.service.StockService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 21:22
 */
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final StockRepository stockRepository;

    private static final QStockPO STOCK_PO = QStockPO.stockPO;


    @Override
    public List<StockVO> listStock() {
        List<StockPO> pos = jpaQueryFactory.selectFrom(STOCK_PO).fetch();

        return pos.stream().map(po -> BeanUtil.toBean(po, StockVO.class)).toList();
    }

    @Override
    public StockVO getStockByProductId(Long pId) {
        StockPO po = jpaQueryFactory.selectFrom(STOCK_PO).where(STOCK_PO.productId.eq(pId)).fetchOne();
        return BeanUtil.toBean(po, StockVO.class);
    }

    @Override
    @Transactional
    public StockVO addStock(StockDTO stockDTO) {
        StockPO po = BeanUtil.toBean(stockDTO, StockPO.class);
        stockRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, StockVO.class);
    }

    @Override
    @Transactional
    public StockVO updateStock(StockDTO stockDTO) {
        jpaQueryFactory.update(STOCK_PO)
                .set(STOCK_PO.productId, stockDTO.getProductId())
                .set(STOCK_PO.amount, stockDTO.getAmount())
                .set(STOCK_PO.warningAmount, stockDTO.getWarningAmount())
                .where(STOCK_PO.stockId.eq(stockDTO.getStockId()))
                .execute();
        StockPO po = jpaQueryFactory.selectFrom(STOCK_PO).where(STOCK_PO.stockId.eq(stockDTO.getStockId())).fetchOne();
        return BeanUtil.toBean(po, StockVO.class);
    }

    @Override
    @Transactional
    public void deleteStock(Long stockId) {
        jpaQueryFactory.delete(STOCK_PO).where(STOCK_PO.stockId.eq(stockId)).execute();
    }

    @Override
    public void createStock(Long productId, Integer amount) {
        StockPO po = StockPO.builder()
                .productId(productId)
                .amount(amount)
                .warningAmount(amount / 10)
                .build();

        stockRepository.save(po);
    }
}
