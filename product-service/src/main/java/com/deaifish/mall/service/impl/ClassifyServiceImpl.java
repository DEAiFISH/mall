package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.config.PathProperties;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.ClassifyDTO;
import com.deaifish.mall.pojo.po.ClassifyPO;
import com.deaifish.mall.pojo.po.QClassifyPO;
import com.deaifish.mall.pojo.vo.ClassifyVO;
import com.deaifish.mall.repository.ClassifyRepository;
import com.deaifish.mall.service.ClassifyService;
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
 * @date 2024/12/28 17:17
 */
@Service
@RequiredArgsConstructor
public class ClassifyServiceImpl implements ClassifyService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final ClassifyRepository classifyRepository;
    private final BIZServiceApi bizServiceApi;
    private final PathProperties pathProperties;

    private static final QClassifyPO CLASSIFY_PO = QClassifyPO.classifyPO;

    @Override
    public List<ClassifyVO> list(String name) {
        List<ClassifyPO> poList = jpaQueryFactory.selectFrom(CLASSIFY_PO)
                .where(createParam(name)).fetch();
        return poList.stream().map(po -> BeanUtil.toBean(po, ClassifyVO.class)).toList();
    }

    @Override
    @Transactional
    public ClassifyVO add(ClassifyDTO classifyDTO) {
        ClassifyPO po = BeanUtil.toBean(classifyDTO, ClassifyPO.class);
        classifyRepository.save(po);
        entityManager.refresh(po);
        return BeanUtil.toBean(po, ClassifyVO.class);
    }

    @Override
    @Transactional
    public ClassifyVO update(ClassifyDTO classifyDTO) {
        jpaQueryFactory.update(CLASSIFY_PO)
                .set(CLASSIFY_PO.parentId, classifyDTO.getParentId())
                .set(CLASSIFY_PO.name, classifyDTO.getName())
                .set(CLASSIFY_PO.number, classifyDTO.getNumber())
                .set(CLASSIFY_PO.description, classifyDTO.getDescription())
                .set(CLASSIFY_PO.icon, classifyDTO.getIcon())
                .where(CLASSIFY_PO.classifyId.eq(classifyDTO.getClassifyId()))
                .execute();

        ClassifyPO po = jpaQueryFactory.selectFrom(CLASSIFY_PO).where(CLASSIFY_PO.classifyId.eq(classifyDTO.getClassifyId())).fetchOne();

        return BeanUtil.toBean(po, ClassifyVO.class);
    }

    @Override
    @Transactional
    public void delete(Integer classifyId) {
        ClassifyPO po = jpaQueryFactory.selectFrom(CLASSIFY_PO).where(CLASSIFY_PO.classifyId.eq(classifyId)).fetchOne();
        if (po == null) {
            throw new MallException("商品不存在");
        }
        bizServiceApi.delete(po.getIcon());
        jpaQueryFactory.delete(CLASSIFY_PO).where(CLASSIFY_PO.classifyId.eq(classifyId)).execute();
    }

    private Predicate[] createParam(String name){
        List<Predicate> param = new ArrayList<>();
        if(StrUtil.isNotBlank(name)){
            param.add(CLASSIFY_PO.name.like("%" + name + "%"));
        }
        return param.toArray(new Predicate[0]);
    }
}
