package com.ndhuy.auth.user.domain.dao.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndhuy.auth.user.domain.dao.IRoleDao;
import com.ndhuy.auth.user.domain.model.Role;
import com.ndhuy.auth.user.domain.model.key.RoleKey;
import com.ndhuy.auth.user.domain.repository.RoleRepository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class RoleDao implements IRoleDao {

    @Resource
    RoleRepository roleRepository;

    /**
     * @param t
     * @return Role
     */
    @Override
    public Role insert(Role t) {
        return roleRepository.save(t);
    }

    /**
     * @param id
     * @param role
     * @return Role
     */
    @Override
    public Role update(RoleKey id, Role role) {
        var roleOld = findById(id);
        Objects.requireNonNull(roleOld, "ROLE_NOT_FOUND");
        return roleRepository.save(role);

    }

    /**
     * @param input
     * @return Role
     */
    @Override
    public Role delete(Role input) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * @param id
     * @return Role
     */
    @Override
    public Role findById(RoleKey id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findById(String id) {
        return findById(RoleKey.of(id));
    }

}
