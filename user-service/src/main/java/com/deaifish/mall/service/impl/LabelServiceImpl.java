package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.LabelDTO;
import com.deaifish.mall.pojo.po.LabelPO;
import com.deaifish.mall.pojo.po.QLabelPO;
import com.deaifish.mall.pojo.po.QUserLabelPO;
import com.deaifish.mall.pojo.po.UserLabelPO;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.repository.LabelRepository;
import com.deaifish.mall.repository.UserLabelRepository;
import com.deaifish.mall.service.LabelService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final EntityManager entityManager;
    private final UserLabelRepository userLabelRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private static final QLabelPO LABEL_PO = QLabelPO.labelPO;
    private static final QUserLabelPO USER_LABEL_PO = QUserLabelPO.userLabelPO;


    @Override
    public List<LabelVO> list(String name) {
        List<LabelPO> poList = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO)
                .where(createParam(name)).fetch();
        List<LabelVO> list = poList.stream().map(label -> BeanUtil.toBean(label, LabelVO.class)).toList();
        return list;
    }

    @Override
    @Transactional
    public LabelVO add(LabelDTO labelDTO) {
        LabelPO po = jpaQueryFactory.select(LABEL_PO).from(LABEL_PO).where(LABEL_PO.name.eq(labelDTO.getName())).fetchOne();
        if (po != null) {
            throw new MallException("标签已存在");
        }
        po = BeanUtil.toBean(labelDTO, LabelPO.class);
        labelRepository.save(po);

        entityManager.refresh(po);
        return BeanUtil.toBean(po, LabelVO.class);
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
        LabelPO po = jpaQueryFactory.selectFrom(LABEL_PO).where(LABEL_PO.labelId.eq(labelDTO.getLabelId())).fetchOne();
        if (po == null) {
            throw new MallException("更新失败");
        }
        return BeanUtil.toBean(po, LabelVO.class);
    }

    @Override
    @Transactional
    public void delete(Integer labelId) {
        jpaQueryFactory.delete(LABEL_PO).where(LABEL_PO.labelId.eq(labelId)).execute();
    }

    @Override
    @Transactional
    public void interestUpdate(List<Integer> ids, Long userID, Integer number) {
        List<UserLabelPO> list = ids.stream().map(id -> {
            UserLabelPO userLabelPO = jpaQueryFactory.select(USER_LABEL_PO).from(USER_LABEL_PO).where(USER_LABEL_PO.labelId.eq(id).and(USER_LABEL_PO.userId.eq(userID))).fetchOne();
            Long weight = jpaQueryFactory.select(LABEL_PO.weights).from(LABEL_PO).where(LABEL_PO.labelId.eq(id)).fetchOne();
            if (userLabelPO == null) {
                userLabelPO = UserLabelPO.builder().userId(userID).labelId(id).interest(weight).build();
            } else {
                userLabelPO.addInterest(weight,number);
            }
            return userLabelPO;
        }).toList();
        userLabelRepository.saveAll(list);
    }

    @Override
    public List<UserInterestVO> interestList(Long userID) {
        return jpaQueryFactory.select(Projections.bean(UserInterestVO.class, LABEL_PO.name.as("label"), USER_LABEL_PO.interest.as("value")))
                .from(USER_LABEL_PO).leftJoin(LABEL_PO).on(USER_LABEL_PO.labelId.eq(LABEL_PO.labelId))
                .where(USER_LABEL_PO.userId.eq(userID))
                .orderBy(USER_LABEL_PO.interest.desc()).fetch();
    }

    @Override
    @Transactional
    public void interestDelete(Long userID) {
        jpaQueryFactory.delete(USER_LABEL_PO).where(USER_LABEL_PO.userId.eq(userID)).execute();
    }

    private Predicate[] createParam(String name){
        List<Predicate> param = new ArrayList<>();
        if(StrUtil.isNotBlank(name)){
            param.add(LABEL_PO.name.like("%" + name + "%"));
        }
        return param.toArray(new Predicate[0]);
    }
}
