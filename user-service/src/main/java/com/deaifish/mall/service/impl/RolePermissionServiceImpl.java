package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.pojo.dto.RolePermissionDTO;
import com.deaifish.mall.pojo.po.*;
import com.deaifish.mall.pojo.qo.RolePermissionQO;
import com.deaifish.mall.pojo.vo.RolePermissionVO;
import com.deaifish.mall.repository.RolePermissionRepository;
import com.deaifish.mall.service.RolePermissionService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

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

    private static final QRolePO ROLE_PO = QRolePO.rolePO;
    private static final QPermissionPO PERMISSION_PO = QPermissionPO.permissionPO;
    private static final QRolePermissionPO ROLE_PERMISSION_PO = QRolePermissionPO.rolePermissionPO;
    private static final QUserPO USER_PO = QUserPO.userPO;

    @Override
    public List<RolePermissionVO> list(RolePermissionQO qo) {
        Predicate[] param = createParam(qo);

        List<RolePermissionPO> pos = jpaQueryFactory.selectFrom(ROLE_PERMISSION_PO).where(param).fetch();

        return rolePermissionPo2Vo(pos);
    }

    @Override
    @Transactional
    public RolePermissionVO add(RolePermissionDTO rolePermissionDTO) {

        if (isExist(rolePermissionDTO)) {
            throw new MallException("添加失败，该角色已拥有此权限");
        }

        Long userId = AuthUserContext.get().getUserId();
        RolePermissionPO po = BeanUtil.toBean(rolePermissionDTO, RolePermissionPO.class);
        po.setUserId(userId);

        po = rolePermissionRepository.save(po);

        entityManager.refresh(po);

        return rolePermissionPo2Vo(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public RolePermissionVO update(RolePermissionDTO rolePermissionDTO) {
        if (isExist(rolePermissionDTO)) {
            throw new MallException("更新失败，该角色已拥有此权限");
        }
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

    @Override
    @Transactional
    public Boolean deleteRolePermission(RolePermissionDTO rolePermissionDTO) {
        jpaQueryFactory.delete(ROLE_PERMISSION_PO).where(
                ROLE_PERMISSION_PO.roleId.eq(rolePermissionDTO.getRoleId())
                        .and(ROLE_PERMISSION_PO.permissionId.eq(rolePermissionDTO.getPermissionId()))
        ).execute();
        return true;
    }

    private List<RolePermissionVO> rolePermissionPo2Vo(List<RolePermissionPO> pos) {
        return pos.stream().map(po -> {
            RolePermissionVO vo = BeanUtil.toBean(po, RolePermissionVO.class);
            RolePO rolePo = jpaQueryFactory.select(ROLE_PO).from(ROLE_PO).where(ROLE_PO.roleId.eq(po.getRoleId())).fetchOne();
            if (rolePo != null) {
                vo.setRoleName(rolePo.getName());
                vo.setRoleDescription(rolePo.getDescription());
            }
            PermissionPO permissionPo = jpaQueryFactory.select(PERMISSION_PO).from(PERMISSION_PO).where(PERMISSION_PO.permissionId.eq(po.getPermissionId())).fetchOne();
            if (permissionPo != null) {
                vo.setPermissionName(permissionPo.getName());
                vo.setPermissionDescription(permissionPo.getDescription());
            }
            String nickName = jpaQueryFactory.select(USER_PO.nickName).from(USER_PO).where(USER_PO.userId.eq(po.getUserId())).fetchOne();
            vo.setNickName(nickName);
            return vo;
        }).toList();
    }

    private Predicate[] createParam(RolePermissionQO qo) {

        List<Predicate> predicates = new ArrayList<>();

        if (qo.getRoleId() != null) {
            predicates.add(ROLE_PERMISSION_PO.roleId.eq(qo.getRoleId()));
        }
        if (qo.getPermissionId() != null) {
            predicates.add(ROLE_PERMISSION_PO.permissionId.eq(qo.getPermissionId()));
        }

        return predicates.toArray(new Predicate[0]);
    }

    private boolean isExist(RolePermissionDTO dto) {
        Byte roleId = dto.getRoleId();
        Byte permissionId = dto.getPermissionId();
        return jpaQueryFactory.select(ROLE_PERMISSION_PO.rolePermissionId).from(ROLE_PERMISSION_PO).where(
                ROLE_PERMISSION_PO.roleId.eq(roleId).and(ROLE_PERMISSION_PO.permissionId.eq(permissionId))
        ).fetchFirst() != null;
    }
}