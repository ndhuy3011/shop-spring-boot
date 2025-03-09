package com.ndhuy.auth.user.domain.dao.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndhuy.auth.user.domain.dao.IRoleAUserDao;
import com.ndhuy.auth.user.domain.dao.IRoleDao;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.repository.RoleAUserRepository;
import com.ndhuy.auth.user.domain.valueobject.RoleAUserKey;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class RoleAUserDao implements IRoleAUserDao {
    @Resource
    RoleAUserRepository roleAUserRepository;
    @Resource
    IRoleDao roleDao;
    @Resource
    IUserDao userDao;

    @Override
    public RoleAUser insert(RoleAUser input) {
        Objects.requireNonNull(roleDao.findById(input.getId().idRole()), "Role not found");
        Objects.requireNonNull(userDao.findById(input.getId().idUser()), "User not found");
        return roleAUserRepository.save(input);
    }

    @Override
    public RoleAUser update(RoleAUserKey id, RoleAUser input) {
        Objects.requireNonNull(roleDao.findById(input.getId().idRole()), "Role not found");
        Objects.requireNonNull(userDao.findById(input.getId().idUser()), "User not found");
        return roleAUserRepository.save(input);
    }

    @Override
    public RoleAUser delete(RoleAUser input) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public RoleAUser findById(RoleAUserKey id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
