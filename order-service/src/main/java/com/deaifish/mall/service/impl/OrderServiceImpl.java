package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.api.ProductServiceApi;
import com.deaifish.mall.config.OrderProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.contanst.OrderCancelReason;
import com.deaifish.mall.pojo.contanst.OrderStatus;
import com.deaifish.mall.pojo.dto.OrderDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.qo.OrderQO;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.repository.OrderRepository;
import com.deaifish.mall.service.OrderService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private final OrderProperties orderProperties;

    private static final QOrderPO ORDER_PO = QOrderPO.orderPO;
    private static final QUserPO USER_PO = QUserPO.userPO;
    private static final QProductPO PRODUCT_PO = QProductPO.productPO;
    private static final QShippingAddressPO ADDRESS_PO = QShippingAddressPO.shippingAddressPO;

    @Override
    public List<OrderVO> list(OrderQO qo) {
        Predicate[] param = createParam(qo);
        return orderPo2Vo(param);
    }

    private Predicate[] createParam(OrderQO qo) {
        List<Predicate> predicates = new ArrayList<>();

        if (qo.getUserId() != null) {
            predicates.add(ORDER_PO.userId.eq(qo.getUserId()));
        }
        if (qo.getMoneyMax() != null && qo.getMoneyMin() != null) {
            predicates.add(ORDER_PO.money.between(qo.getMoneyMin(), qo.getMoneyMax()));
        }
        if (qo.getPaymentMethod() != null) {
            predicates.add(ORDER_PO.paymentMethod.eq(qo.getPaymentMethod()));
        }
        if (qo.getStatus() != null) {
            predicates.add(ORDER_PO.status.eq(qo.getStatus()));
        }
        if (qo.getPayTimeFrom() != null && qo.getPayTimeTo() != null) {
            predicates.add(ORDER_PO.payTime.between(qo.getPayTimeFrom(), qo.getPayTimeTo()));
        }
        if (qo.getShipTimeFrom() != null && qo.getShipTimeTo() != null) {
            predicates.add(ORDER_PO.shipTime.between(qo.getShipTimeFrom(), qo.getShipTimeTo()));
        }
        if (qo.getFinishTimeFrom() != null && qo.getFinishTimeTo() != null) {
            predicates.add(ORDER_PO.finishTime.between(qo.getFinishTimeFrom(), qo.getFinishTimeTo()));
        }
        if (qo.getCancelTimeFrom() != null && qo.getCancelTimeTo() != null) {
            predicates.add(ORDER_PO.cancelTime.between(qo.getCancelTimeFrom(), qo.getCancelTimeTo()));
        }
        if (qo.getIsPay() != null) {
            predicates.add(ORDER_PO.isPay.eq(qo.getIsPay()));
        }
        if (qo.getCancelReason() != null) {
            predicates.add(ORDER_PO.cancelReason.eq(qo.getCancelReason()));
        }

        return predicates.toArray(new Predicate[0]);
    }


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
        BooleanExpression whereParam = ORDER_PO.userId.eq(userId);

        return orderPo2Vo(whereParam);
    }

    @Override
    public List<OrderVO> getByProductId(Long productId) {
        BooleanExpression whereParam = ORDER_PO.productId.eq(productId);
        return orderPo2Vo(whereParam);
    }

    @Override
    public boolean checkOrderIsTimeout(Long orderId) {
        OrderPO po = orderRedisTemplate.opsForValue().get(orderProperties.getRedisOrderKey() + orderId);
        return po == null;
    }

    @Override
    @Transactional
    public OrderVO pay(Long orderId, Byte paymentMethod) {
        // TODO: 支付逻辑

        OrderPO po = orderRedisTemplate.opsForValue().get(orderProperties.getRedisOrderKey() + orderId);
        if (po == null) {
            throw new MallException("订单已超时，请重新下单");
        }
        if(po.getStatus() != OrderStatus.WAIT_PAY.getCode()){
            throw new MallException("订单状态不正确，无法支付");
        }
        po.setPaymentMethod(paymentMethod);
        po.setPayTime(new Date());
        po.setStatus((byte) 2);
        po.setIsPay(true);
        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.paymentMethod, paymentMethod)
                .set(ORDER_PO.payTime, new Date())
                .set(ORDER_PO.status, OrderStatus.WAIT_SEND.getCode())
                .set(ORDER_PO.isPay, true)
                .where(ORDER_PO.orderId.eq(orderId)).execute();
        orderRedisTemplate.delete(orderProperties.getRedisOrderKey() + orderId);

        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO send(Long orderId, String courierNumber) {
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }
        if(po.getStatus() != OrderStatus.WAIT_SEND.getCode()){
            throw new MallException("订单状态不正确，无法发货");
        }


        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.courierNumber, courierNumber)
                .set(ORDER_PO.shipTime, new Date())
                .set(ORDER_PO.status, OrderStatus.WAIT_RECEIVE.getCode())
                .where(ORDER_PO.orderId.eq(orderId))
                .execute();

        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO receive(Long orderId) {
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }
        if(po.getStatus() != OrderStatus.WAIT_SEND.getCode()){
            throw new MallException("订单状态不正确，无法收货");
        }

        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.status, OrderStatus.WAIT_EVALUATE.getCode())
                .where(ORDER_PO.orderId.eq(orderId))
                .execute();
        return getByOrderId(orderId);
    }


    @Override
    @Transactional
    public OrderVO finish(Long orderId) {
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }
        if(po.getStatus() != OrderStatus.WAIT_SEND.getCode()){
            throw new MallException("订单状态不正确，无法完成订单操作");
        }

        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.finishTime, new Date())
                .set(ORDER_PO.status, OrderStatus.FINISH.getCode())
                .where(ORDER_PO.orderId.eq(orderId))
                .execute();
        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO cancel(Long orderId, OrderCancelReason cancelReason) {

        orderRedisTemplate.delete(orderProperties.getRedisOrderKey() + orderId);

        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.cancelTime, new Date())
                .set(ORDER_PO.status, OrderStatus.FAIL.getCode())
                .set(ORDER_PO.cancelReason, cancelReason.getCode())
                .where(ORDER_PO.orderId.eq(orderId))
                .execute();

        OrderPO orderPO = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (orderPO == null) {
            throw new MallException("订单不存在");
        }
        productServiceApi.reduceStock(Math.negateExact(orderPO.getAmount()),orderPO.getProductId());
        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO updateMemos(Long orderId, String memos) {
        jpaQueryFactory.update(ORDER_PO)
                .set(ORDER_PO.memo, memos)
                .where(ORDER_PO.orderId.eq(orderId))
                .execute();
        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO updateAddress(Long orderId, Long addressId) {
        jpaQueryFactory.update(ORDER_PO).set(ORDER_PO.addressId, addressId).where(ORDER_PO.orderId.eq(orderId)).execute();
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }
        orderRedisTemplate.delete(orderProperties.getRedisOrderKey() + orderId);
        orderRedisTemplate.opsForValue().set(orderProperties.getRedisOrderKey() + orderId, po);

        return getByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderVO add(OrderDTO orderDTO) {
        OrderPO po = BeanUtil.toBean(orderDTO, OrderPO.class);

        ProductVO productVO = productServiceApi.getProductById(orderDTO.getProductId()).getData();
        Integer stock = productVO.getStock();
        if (stock == null) {
            throw new MallException("商品库存异常，请联系管理员");
        }
        if (stock < orderDTO.getAmount()) {
            throw new MallException("库存不足");
        }
        if (!productServiceApi.reduceStock(orderDTO.getAmount(), orderDTO.getProductId()).getData()) {
            throw new MallException("订单异常，请稍后再试");
        }

        double price = productVO.getPrice();
        if (productVO.getPreferentialPrice() != null) {
            price = productVO.getPreferentialPrice();
        }
        BigDecimal money = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(orderDTO.getAmount()));
        po.setMoney(money.doubleValue());
        orderRepository.save(po);
        entityManager.refresh(po);
        orderRedisTemplate.opsForValue().set(orderProperties.getRedisOrderKey() + po.getOrderId(), po, orderProperties.getRedisOrderTimeout(), TimeUnit.MINUTES);
        return getByOrderId(po.getOrderId());
    }

    @Override
    @Transactional
    public void delete(Long orderId) {
        OrderPO po = jpaQueryFactory.selectFrom(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).fetchOne();
        if (po == null) {
            throw new MallException("订单不存在");
        }

        if (!productServiceApi.reduceStock(-po.getAmount(), po.getProductId()).getData()) {
            throw new MallException("订单异常，请稍后再试");
        }
        orderRedisTemplate.delete(orderProperties.getRedisOrderKey() + po.getOrderId());
        jpaQueryFactory.delete(ORDER_PO).where(ORDER_PO.orderId.eq(orderId)).execute();
    }

    /**
     * 将OrderPO转为OrderVO
     * @param whereParam 查询条件
     * @return
     */
    private List<OrderVO> orderPo2Vo(Predicate... whereParam) {
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

            vo.setStatus(OrderStatus.getDescByCode(po.getStatus()));
            if(po.getCancelReason() != null){
                vo.setCancelReason(OrderCancelReason.getDescByCode(po.getCancelReason()));
            }
            return vo;

        }).toList();
    }
}
