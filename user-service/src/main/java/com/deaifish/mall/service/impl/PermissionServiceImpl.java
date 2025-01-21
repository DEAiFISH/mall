package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.pojo.dto.PermissionDTO;
import com.deaifish.mall.pojo.po.PermissionPO;
import com.deaifish.mall.pojo.po.QPermissionPO;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.pojo.vo.PermissionVO;
import com.deaifish.mall.repository.PermissionRepository;
import com.deaifish.mall.service.PermissionService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
 * @date 2025/1/13 14:36
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final PermissionRepository permissionRepository;

    private static final QPermissionPO PERMISSION_PO = QPermissionPO.permissionPO;
    private static final QUserPO USER_PO = QUserPO.userPO;

    @Override
    public List<PermissionVO> list(String name) {

        List<PermissionPO> pos = jpaQueryFactory.selectFrom(PERMISSION_PO)
                .where(createParam(name)).fetch();

        return permissionPo2Vo(pos);
    }

    @Override
    @Transactional
    public PermissionVO add(PermissionDTO permissionDTO) {
        Long userId = AuthUserContext.get().getUserId();
        PermissionPO po = BeanUtil.toBean(permissionDTO, PermissionPO.class);
        po.setUserId(userId);

        po = permissionRepository.save(po);
        entityManager.refresh(po);
        return permissionPo2Vo(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public PermissionVO update(PermissionDTO permissionDTO) {
        jpaQueryFactory.update(PERMISSION_PO)
                .set(PERMISSION_PO.name, permissionDTO.getName())
                .set(PERMISSION_PO.description, permissionDTO.getDescription())
                .set(PERMISSION_PO.userId, AuthUserContext.get().getUserId())
                .where(PERMISSION_PO.permissionId.eq(permissionDTO.getPermissionId())).execute();
        PermissionPO po = jpaQueryFactory.selectFrom(PERMISSION_PO).where(PERMISSION_PO.permissionId.eq(permissionDTO.getPermissionId())).fetchOne();
        return permissionPo2Vo(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public Boolean delete(Byte id) {
        permissionRepository.deleteById(id);
        return true;
    }

    private List<PermissionVO> permissionPo2Vo(List<PermissionPO> pos) {
        return pos.stream().map(po -> {
            PermissionVO vo = BeanUtil.toBean(po, PermissionVO.class);
            String nickName = jpaQueryFactory.select(USER_PO.nickName).from(USER_PO).where(USER_PO.userId.eq(po.getUserId())).fetchOne();
            vo.setNickName(nickName);
            return vo;
        }).toList();
    }

    private Predicate[] createParam(String name){
        List<Predicate> param = new ArrayList<>();
        if (name != null) {
            param.add(PERMISSION_PO.name.like("%" + name + "%"));
        }
        return param.toArray(new Predicate[0]);
    }
}