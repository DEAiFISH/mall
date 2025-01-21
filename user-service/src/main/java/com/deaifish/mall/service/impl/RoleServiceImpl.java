package com.deaifish.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.AuthUserContext;
import com.deaifish.mall.pojo.dto.RoleDTO;
import com.deaifish.mall.pojo.po.QRolePO;
import com.deaifish.mall.pojo.po.QUserPO;
import com.deaifish.mall.pojo.po.RolePO;
import com.deaifish.mall.pojo.vo.RoleVO;
import com.deaifish.mall.repository.RoleRepository;
import com.deaifish.mall.service.RoleService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/13 14:18
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;

    private static final QRolePO ROLE_PO = QRolePO.rolePO;
    private static final QUserPO USER_PO = QUserPO.userPO;

    @Override
    public List<RoleVO> list(String name) {
        createParam(name);
        List<RolePO> pos = jpaQueryFactory.selectFrom(ROLE_PO)
                .where(createParam(name)).fetch();

        return rolePO2VO(pos);
    }

    @Override
    @Transactional
    public RoleVO add(RoleDTO roleDTO) {
        RolePO po = BeanUtil.toBean(roleDTO, RolePO.class);
        po.setUserId(AuthUserContext.get().getUserId());
        po = roleRepository.save(po);

        entityManager.refresh(po);
        return rolePO2VO(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public RoleVO update(RoleDTO roleDTO) {
        jpaQueryFactory.update(ROLE_PO)
                .set(ROLE_PO.name, roleDTO.getName())
                .set(ROLE_PO.description, roleDTO.getDescription())
                .set(ROLE_PO.userId, AuthUserContext.get().getUserId())
                .set(ROLE_PO.status, roleDTO.getStatus())
                .where(ROLE_PO.roleId.eq(roleDTO.getRoleId())).execute();
        RolePO po = jpaQueryFactory.selectFrom(ROLE_PO).where(ROLE_PO.roleId.eq(roleDTO.getRoleId())).fetchOne();
        return rolePO2VO(List.of(po)).get(0);
    }

    @Override
    @Transactional
    public Boolean delete(Byte roleId) {
        jpaQueryFactory.delete(ROLE_PO).where(ROLE_PO.roleId.eq(roleId)).execute();
        return true;
    }

    private List<RoleVO> rolePO2VO(List<RolePO> pos) {
        return pos.stream().map(po -> {
            RoleVO vo = BeanUtil.toBean(po, RoleVO.class);
            String nickName = jpaQueryFactory.select(USER_PO.nickName).from(USER_PO).where(USER_PO.userId.eq(po.getUserId())).fetchOne();
            vo.setNickName(nickName);
            return vo;
        }).toList();
    }

    private Predicate[] createParam(String name) {
        List<Predicate> param = new ArrayList<>();
        if(StrUtil.isNotBlank(name)){
            param.add(ROLE_PO.name.like("%" + name + "%"));
        }
        return param.toArray(new Predicate[0]);
    }
}