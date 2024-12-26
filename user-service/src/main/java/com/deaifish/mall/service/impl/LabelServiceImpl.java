package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.pojo.dto.LabelDTO;
import com.deaifish.mall.pojo.po.LabelPO;
import com.deaifish.mall.po.QLabelPO;
import com.deaifish.mall.po.QUserLabelPO;
import com.deaifish.mall.pojo.po.UserLabelPO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.repository.LabelRepository;
import com.deaifish.mall.repository.UserLabelRepository;
import com.deaifish.mall.service.LabelService;
import com.querydsl.core.types.Projections;
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
    private UserLabelRepository userLabelRepository;
    @Resource
    private JPAQueryFactory jpaQueryFactory;

    private static final QLabelPO LABEL_PO = QLabelPO.labelPO;
    private static final QUserLabelPO USER_LABEL_PO = QUserLabelPO.userLabelPO;


    @Override
    public List<LabelVO> list() {
        List<LabelPO> poList = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).fetch();
        List<LabelVO> list = poList.stream().map(label -> BeanUtil.toBean(label, LabelVO.class)).toList();
        return list;
    }

    @Override
    @Transactional
    public LabelVO add(LabelDTO labelDTO) {
        LabelPO po = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).where(LABEL_PO.name.eq(labelDTO.getName())).fetchOne();
        if(po != null){
            throw new MallException("标签已存在");
        }
        po = BeanUtil.toBean(labelDTO, LabelPO.class);
        jpaQueryFactory.insert(LABEL_PO).columns(LABEL_PO.name, LABEL_PO.weights, LABEL_PO.description)
                .values(po.getName(), po.getWeights(), po.getDescription()).execute();
        LabelPO label = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).where(LABEL_PO.name.eq(labelDTO.getName())).fetchOne();
        if(label == null){
            throw new MallException("添加失败");
        }
        return BeanUtil.toBean(label, LabelVO.class);
    }

    @Override
    @Transactional
    public LabelVO update(LabelDTO labelDTO) {
        jpaQueryFactory.update(LABEL_PO)
                .set(LABEL_PO.name, labelDTO.getName())
                .set(LABEL_PO.weights, labelDTO.getWeights())
                .set(LABEL_PO.description, labelDTO.getDescription())
                .where(LABEL_PO.labelId.eq(labelDTO.getLabelId()))
                .execute();
        LabelPO label = jpaQueryFactory.selectFrom(LABEL_PO).where(LABEL_PO.labelId.eq(labelDTO.getLabelId())).fetchOne();
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

    @Override
    @Transactional
    public void interestUpdate(List<Integer> ids, Long userID) {
        List<UserLabelPO> list = ids.stream().map(id -> {
            UserLabelPO userLabelPO = jpaQueryFactory.select(USER_LABEL_PO).from(USER_LABEL_PO).where(USER_LABEL_PO.labelId.eq(id).and(USER_LABEL_PO.userId.eq(userID))).fetchOne();
            Long weight = jpaQueryFactory.select(LABEL_PO.weights).from(LABEL_PO).where(LABEL_PO.labelId.eq(id)).fetchOne();
            if (userLabelPO == null) {
                userLabelPO = UserLabelPO.builder().userId(userID).labelId(id).interest(weight).build();
            } else {
                userLabelPO.addInterest(weight);
            }
            return userLabelPO;
        }).toList();
        userLabelRepository.saveAll(list);
    }

    @Override
    public List<UserInterestVO> interestList(Long userID) {
        return jpaQueryFactory.select(Projections.bean(UserInterestVO.class,LABEL_PO.name.as("label"), USER_LABEL_PO.interest.as("value")))
                .from(USER_LABEL_PO).leftJoin(LABEL_PO).on(USER_LABEL_PO.labelId.eq(LABEL_PO.labelId))
                .where(USER_LABEL_PO.userId.eq(userID)).fetch();
    }

    @Override
    @Transactional
    public void interestDelete(Long userID) {
        jpaQueryFactory.delete(USER_LABEL_PO).where(USER_LABEL_PO.userId.eq(userID)).execute();
    }
}
