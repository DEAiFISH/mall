package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.VoucherDTO;
import com.deaifish.mall.pojo.po.QVoucherPO;
import com.deaifish.mall.pojo.po.VoucherPO;
import com.deaifish.mall.pojo.vo.VoucherVO;
import com.deaifish.mall.repository.VoucherRepository;
import com.deaifish.mall.service.VoucherService;
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
    public List<VoucherVO> list() {
        List<VoucherPO> pos = jpaQueryFactory.selectFrom(VOUCHER_PO).fetch();
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
                .where(VOUCHER_PO.voucherId.eq(voucherDTO.getVoucherId())).execute();

        VoucherPO po = jpaQueryFactory.selectFrom(VOUCHER_PO).where(VOUCHER_PO.voucherId.eq(voucherDTO.getVoucherId())).fetchOne();
        return BeanUtil.toBean(po, VoucherVO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jpaQueryFactory.delete(VOUCHER_PO).where(VOUCHER_PO.voucherId.eq(id)).execute();
    }
}