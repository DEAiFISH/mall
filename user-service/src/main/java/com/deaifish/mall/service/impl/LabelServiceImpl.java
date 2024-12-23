package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.entity.dto.LabelDTO;
import com.deaifish.mall.entity.po.LabelPO;
import com.deaifish.mall.entity.po.QLabelPO;
import com.deaifish.mall.entity.vo.LabelVO;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.repository.LabelRepository;
import com.deaifish.mall.service.LabelService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelRepository labelRepository;
    @Resource
    private JPAQueryFactory jpaQueryFactory;

    private static final QLabelPO LABEL_PO = QLabelPO.labelPO;


    @Override
    public List<LabelVO> list() {
        List<LabelPO> poList = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).fetch();
        List<LabelVO> list = poList.stream().map(label -> BeanUtil.toBean(label, LabelVO.class)).toList();
        return list;
    }

    @Override
    @Transactional
    public LabelVO add(LabelDTO labelDTO) {
        jpaQueryFactory.insert(LABEL_PO);
        LabelPO label = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).where(LABEL_PO.name.eq(labelDTO.getName())).fetchOne();
        if(label == null){
            throw new MallException("添加失败");
        }
        return BeanUtil.toBean(label, LabelVO.class);
    }

    @Override
    @Transactional
    public LabelVO update(LabelDTO labelDTO) {
        jpaQueryFactory.update(LABEL_PO);
        LabelPO label = jpaQueryFactory.select(LABEL_PO).where(LABEL_PO.labelId.eq(labelDTO.getLabelId())).fetchOne();
        if(label == null){
            throw new MallException("更新失败");
        }
        return BeanUtil.toBean(label, LabelVO.class);
    }

    @Override
    @Transactional
    public void delete(Integer labelId) {
        jpaQueryFactory.delete(LABEL_PO).where(LABEL_PO.labelId.eq(labelId)).execute();
    }
}
