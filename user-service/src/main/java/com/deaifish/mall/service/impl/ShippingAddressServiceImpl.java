package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.ShippingAddressDTO;
import com.deaifish.mall.pojo.po.QShippingAddressPO;
import com.deaifish.mall.pojo.po.ShippingAddressPO;
import com.deaifish.mall.pojo.vo.ShippingAddressVO;
import com.deaifish.mall.repository.ShippingAddressRepository;
import com.deaifish.mall.service.ShippingAddressService;
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
 * @date 2024/12/24 22:10
 */
@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final ShippingAddressRepository shippingAddressRepository;


    private static final QShippingAddressPO SHIPPING_ADDRESS_PO = QShippingAddressPO.shippingAddressPO;


    @Override
    public List<ShippingAddressVO> listAll(Long uId) {
        List<ShippingAddressPO> list = jpaQueryFactory.selectFrom(SHIPPING_ADDRESS_PO).from(SHIPPING_ADDRESS_PO)
                .where(SHIPPING_ADDRESS_PO.userId.eq(uId)).fetch();

        return list.stream().map(po -> BeanUtil.toBean(po, ShippingAddressVO.class)).toList();
    }

    @Override
    public ShippingAddressVO get(Long saId) {
        ShippingAddressPO po = jpaQueryFactory.selectFrom(SHIPPING_ADDRESS_PO).where(SHIPPING_ADDRESS_PO.addressId.eq(saId)).fetchOne();
        return BeanUtil.toBean(po, ShippingAddressVO.class);
    }

    @Override
    @Transactional
    public ShippingAddressVO add(ShippingAddressDTO shippingAddressDTO) {
        ShippingAddressPO po = BeanUtil.toBean(shippingAddressDTO, ShippingAddressPO.class);
        shippingAddressRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, ShippingAddressVO.class);
    }

    @Override
    @Transactional
    public ShippingAddressVO update(ShippingAddressDTO shippingAddressDTO) {
        ShippingAddressPO po = BeanUtil.toBean(shippingAddressDTO, ShippingAddressPO.class);
        jpaQueryFactory.update(SHIPPING_ADDRESS_PO)
                .set(SHIPPING_ADDRESS_PO.name, po.getName())
                .set(SHIPPING_ADDRESS_PO.province, po.getProvince())
                .set(SHIPPING_ADDRESS_PO.city, po.getCity())
                .set(SHIPPING_ADDRESS_PO.area, po.getArea())
                .set(SHIPPING_ADDRESS_PO.street, po.getStreet())
                .set(SHIPPING_ADDRESS_PO.full, po.getFull())
                .set(SHIPPING_ADDRESS_PO.phone, po.getPhone())
                .where(SHIPPING_ADDRESS_PO.addressId.eq(po.getAddressId())).execute();
        po = jpaQueryFactory.selectFrom(SHIPPING_ADDRESS_PO).where(SHIPPING_ADDRESS_PO.addressId.eq(po.getAddressId())).fetchOne();
        return BeanUtil.toBean(po, ShippingAddressVO.class);
    }

    @Override
    @Transactional
    public Boolean delete(Long saId) {
        jpaQueryFactory.delete(SHIPPING_ADDRESS_PO).where(SHIPPING_ADDRESS_PO.addressId.eq(saId)).execute();
        return true;
    }
}