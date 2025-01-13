package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.ProductServiceApi;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.repository.OrderRepository;
import com.deaifish.mall.service.OrderService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/2 23:02
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final OrderRepository orderRepository;
    private final ProductServiceApi productServiceApi;
    private final RedisTemplate<String, OrderPO> orderRedisTemplate;

    private static final QOrderPO ORDER_PO = QOrderPO.orderPO;
    private static final QUserPO USER_PO = QUserPO.userPO;
    private static final QProductPO PRODUCT_PO = QProductPO.productPO;
    private static final QShippingAddressPO ADDRESS_PO = QShippingAddressPO.shippingAddressPO;
    private static final String REDIS_ORDER_KEY = "order:";

    @Override
    public OrderVO getByOrderId(Long orderId) {
        BooleanExpression whereParam = ORDER_PO.orderId.eq(orderId);

        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new RuntimeException("订单不存在");
        }

        return orderPo2Vo(whereParam).get(0);
    }

    @Override
    public List<OrderVO> getByUserId(Long userId) {
        BooleanExpression whereParam = ORDER_PO.orderId.eq(userId);

        return orderPo2Vo(whereParam);
    }

    @Override
    public List<OrderVO> getByProductId(Long productId) {
        BooleanExpression whereParam = ORDER_PO.orderId.eq(productId);
        return orderPo2Vo(whereParam);
    }

    @Override
    public boolean checkOrderIsTimeout(Long orderId) {
        OrderPO po = orderRedisTemplate.opsForValue().get(REDIS_ORDER_KEY + orderId);
        return po == null;
    }

    @Override
    public OrderVO update(OrderDTO orderDTO) {

        return null;
    }

    @Override
    @Transactional
    public OrderVO updateStatus(Long orderId, Byte status) {
        jpaQueryFactory.update(ORDER_PO).set(ORDER_PO.status, status).where(ORDER_PO.orderId.eq(orderId)).execute();
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        orderRedisTemplate.delete(REDIS_ORDER_KEY + orderId);
        orderRedisTemplate.opsForValue().set(REDIS_ORDER_KEY + orderId, po);
        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO updateAddress(Long orderId, Long addressId) {
        jpaQueryFactory.update(ORDER_PO).set(ORDER_PO.addressId, addressId).where(ORDER_PO.orderId.eq(orderId)).execute();
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        orderRedisTemplate.delete(REDIS_ORDER_KEY + orderId);
        orderRedisTemplate.opsForValue().set(REDIS_ORDER_KEY + orderId, po);
        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO add(OrderDTO orderDTO) {
        OrderPO po = BeanUtil.toBean(orderDTO, OrderPO.class);

        StockVO stockVO = productServiceApi.getStockByProductId(po.getProductId()).getData();
        if (stockVO == null) {
            throw new MallException("商品库存异常，请联系管理员");
        }
        if (stockVO.getAmount() < orderDTO.getAmount()) {
            throw new MallException("库存不足");
        } else {
            stockVO.setAmount(stockVO.getAmount() - orderDTO.getAmount());
        }
        productServiceApi.updateStock(BeanUtil.toBean(stockVO, StockDTO.class));

        orderRepository.save(po);
        entityManager.refresh(po);
        orderRedisTemplate.opsForValue().set(REDIS_ORDER_KEY + po.getOrderId(), po);
        return getByOrderId(po.getOrderId());
    }

    @Override
    @Transactional
    public void delete(Long orderId) {
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }

        StockVO stockVO = productServiceApi.getStockByProductId(po.getProductId()).getData();
        if (stockVO == null) {
            throw new MallException("商品库存异常，请联系管理员");
        }
        stockVO.setAmount(stockVO.getAmount() + po.getAmount());
        productServiceApi.updateStock(BeanUtil.toBean(stockVO, StockDTO.class));
        orderRedisTemplate.delete(REDIS_ORDER_KEY + po.getOrderId());
        jpaQueryFactory.delete(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).execute();
    }

    /**
     * 将OrderPO转为OrderVO
     * @param whereParam 查询条件
     * @return
     */
    private List<OrderVO> orderPo2Vo(BooleanExpression whereParam) {
        List<OrderPO> pos = jpaQueryFactory.select(ORDER_PO).from(ORDER_PO).where(whereParam).fetch();

        return pos.stream().map(po -> {
            OrderVO vo = BeanUtil.toBean(po, OrderVO.class);

            Long userId = po.getUserId();
            String username = jpaQueryFactory.select(USER_PO.nickName).from(USER_PO).where(USER_PO.userId.eq(userId)).fetchOne();
            vo.setUsername(username);

            Long productId = po.getProductId();
            ProductPO productPo = jpaQueryFactory.selectFrom(PRODUCT_PO).where(PRODUCT_PO.productId.eq(productId)).fetchOne();
            if (productPo != null) {
                String productName = productPo.getName();
                String coverPicture = productPo.getCoverPicture();
                vo.setProductName(productName);
                vo.setProductImage(coverPicture);
            }

            Long addressId = po.getAddressId();
            ShippingAddressPO addressPo = jpaQueryFactory.selectFrom(ADDRESS_PO).where(ADDRESS_PO.addressId.eq(addressId)).fetchOne();
            if (addressPo != null) {
                vo.setAddress(addressPo.getAddress());
            }

            return vo;

        }).toList();
    }
}
