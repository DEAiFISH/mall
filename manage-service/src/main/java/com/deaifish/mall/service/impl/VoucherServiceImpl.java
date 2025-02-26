package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.pojo.dto.VoucherDTO;
import com.deaifish.mall.pojo.po.QVoucherPO;
import com.deaifish.mall.pojo.po.VoucherPO;
import com.deaifish.mall.pojo.vo.VoucherVO;
import com.deaifish.mall.repository.VoucherRepository;
import com.deaifish.mall.service.VoucherService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/5 23:00
 */
@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final VoucherRepository voucherRepository;

    private static final QVoucherPO VOUCHER_PO = QVoucherPO.voucherPO;


    @Override
    public List<VoucherVO> list( String name) {
        List<VoucherPO> pos = jpaQueryFactory.selectFrom(VOUCHER_PO)
                .where(createParam(name)).fetch();
        return pos.stream().map(po -> BeanUtil.toBean(po, VoucherVO.class)).toList();
    }

    @Override
    @Transactional
    public VoucherVO add(VoucherDTO voucherDTO) {
        VoucherPO po = BeanUtil.toBean(voucherDTO, VoucherPO.class);
        voucherRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, VoucherVO.class);
    }

    @Override
    @Transactional
    public VoucherVO update(VoucherDTO voucherDTO) {
        jpaQueryFactory.update(VOUCHER_PO)
                .set(VOUCHER_PO.name, voucherDTO.getName())
                .set(VOUCHER_PO.description, voucherDTO.getDescription())
                .set(VOUCHER_PO.price, voucherDTO.getPrice())
                .set(VOUCHER_PO.amount, voucherDTO.getAmount())
                .set(VOUCHER_PO.status, voucherDTO.getStatus())
                .where(VOUCHER_PO.voucherId.eq(voucherDTO.getVoucherId())).execute();

        VoucherPO po = jpaQueryFactory.selectFrom(VOUCHER_PO).where(VOUCHER_PO.voucherId.eq(voucherDTO.getVoucherId())).fetchOne();
        return BeanUtil.toBean(po, VoucherVO.class);
    }

    @Override
    @Transactional
    public VoucherVO receive(Long voucherId) {
        VoucherPO po = jpaQueryFactory.selectFrom(VOUCHER_PO).where(VOUCHER_PO.voucherId.eq(voucherId)).fetchOne();
        if(po == null || po.getStatus() == 0 || po.getAmount() <= 0){
            throw new RuntimeException("优惠券不存在或已被领取完");
        }

        po.setAmount(po.getAmount() - 1);
        if(po.getAmount() == 0){
            po.setStatus((byte) 0);
        }

        jpaQueryFactory.update(VOUCHER_PO)
                .set(VOUCHER_PO.amount, po.getAmount())
                .set(VOUCHER_PO.status, po.getStatus())
                .where(VOUCHER_PO.voucherId.eq(po.getVoucherId())).execute();

        entityManager.refresh(po);
        return BeanUtil.toBean(po, VoucherVO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jpaQueryFactory.delete(VOUCHER_PO).where(VOUCHER_PO.voucherId.eq(id)).execute();
    }

    private Predicate[] createParam(String name){
        List<Predicate> param = new ArrayList<>();
        if(StrUtil.isNotBlank(name)){
            param.add(VOUCHER_PO.name.like("%"+name+"%"));
        }
        return param.toArray(new Predicate[0]);
    }
}