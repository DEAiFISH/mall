package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.pojo.dto.RolePermissionDTO;
import com.deaifish.mall.pojo.po.QRolePermissionPO;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.pojo.po.RolePermissionPO;
import com.deaifish.mall.pojo.vo.RolePermissionVO;
import com.deaifish.mall.repository.RolePermissionRepository;
import com.deaifish.mall.service.RolePermissionService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:17
 */
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final RolePermissionRepository rolePermissionRepository;

    private static final QRolePermissionPO ROLE_PERMISSION_PO = QRolePermissionPO.rolePermissionPO;
    private static final QUserPO USER_PO = QUserPO.userPO;

    @Override
    public List<RolePermissionVO> list() {
        List<RolePermissionPO> pos = jpaQueryFactory.selectFrom(ROLE_PERMISSION_PO).fetch();

        return rolePermissionPo2Vo(pos);
    }

    @Override
    @Transactional
    public RolePermissionVO add(RolePermissionDTO rolePermissionDTO) {
        Long userId = AuthUserContext.get().getUserId();
        RolePermissionPO po = BeanUtil.toBean(rolePermissionDTO, RolePermissionPO.class);
        po.setUserId(userId);

        po = rolePermissionRepository.save(po);

        entityManager.refresh(po);

        return rolePermissionPo2Vo(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public List<RolePermissionVO> addBatch(List<RolePermissionDTO> rolePermissionDTOs) {
        Long userId = AuthUserContext.get().getUserId();
        List<RolePermissionPO> pos = rolePermissionDTOs.stream().map(dto -> {
            RolePermissionPO po = BeanUtil.toBean(dto, RolePermissionPO.class);
            po.setUserId(userId);
            return po;
        }).toList();

        pos = rolePermissionRepository.saveAll(pos);

        pos.stream().forEach(entityManager::refresh);

        return rolePermissionPo2Vo(pos);
    }

    @Override
    @Transactional
    public RolePermissionVO update(RolePermissionDTO rolePermissionDTO) {
        jpaQueryFactory.update(ROLE_PERMISSION_PO)
                .set(ROLE_PERMISSION_PO.permissionId, rolePermissionDTO.getPermissionId())
                .set(ROLE_PERMISSION_PO.roleId, rolePermissionDTO.getRoleId())
                .set(ROLE_PERMISSION_PO.userId, AuthUserContext.get().getUserId())
                .where(ROLE_PERMISSION_PO.rolePermissionId.eq(rolePermissionDTO.getRolePermissionId())).execute();

        RolePermissionPO po = jpaQueryFactory.selectFrom(ROLE_PERMISSION_PO).where(ROLE_PERMISSION_PO.rolePermissionId.eq(rolePermissionDTO.getRolePermissionId())).fetchOne();
        return rolePermissionPo2Vo(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public Boolean delete(Byte rolePermissionId) {
        jpaQueryFactory.delete(ROLE_PERMISSION_PO).where(ROLE_PERMISSION_PO.rolePermissionId.eq(rolePermissionId)).execute();
        return true;
    }

    private List<RolePermissionVO> rolePermissionPo2Vo(List<RolePermissionPO> pos) {
        return pos.stream().map(po -> {
            RolePermissionVO vo = BeanUtil.toBean(po, RolePermissionVO.class);
            String nickName = jpaQueryFactory.select(USER_PO.nickName).from(USER_PO).where(USER_PO.userId.eq(po.getUserId())).fetchOne();
            vo.setNickName(nickName);
            return vo;
        }).toList();
    }
}