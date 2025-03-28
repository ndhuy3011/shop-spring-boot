package com.ndhuy.auth.user.domain.dao.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndhuy.auth.user.domain.dao.IRoleAUserDao;
import com.ndhuy.auth.user.domain.dao.IRoleDao;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;
import com.ndhuy.auth.user.domain.repository.RoleAUserRepository;

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

    /**
     * Inserts a new RoleAUser (role-user association) into the database.
     *
     * @param input The RoleAUser object to insert.
     * @return The inserted RoleAUser object.
     * @throws NullPointerException if the role or user associated with the
     *                              RoleAUser
     *                              does not exist.
     */
    @Override
    public RoleAUser insert(RoleAUser input) {
        Objects.requireNonNull(roleDao.findById(input.getId().idRole()), "ROLE_NOT_FOUND");
        Objects.requireNonNull(userDao.findById(input.getId().idUser()), "USER_NOT_FOUND");
        return roleAUserRepository.save(input);
    }

    /**
     * Updates an existing RoleAUser (role-user association) in the database.
     *
     * @param id    The RoleAUserKey of the RoleAUser object to update.
     * @param input The updated RoleAUser object.
     * @return The updated RoleAUser object.
     * @throws NullPointerException if the role or user associated with the
     *                              RoleAUser
     *                              does not exist.
     */
    @Override
    public RoleAUser update(RoleAUserKey id, RoleAUser input) {
        Objects.requireNonNull(roleDao.findById(input.getId().idRole()), "ROLE_NOT_FOUND");
        Objects.requireNonNull(userDao.findById(input.getId().idUser()), "USER_NOT_FOUND");
        return roleAUserRepository.save(input);
    }

    /**
     * Deletes a RoleAUser (role-user association) from the database.
     *
     * @param input The RoleAUser object to delete.
     * @return The deleted RoleAUser object.
     */
    @Override
    public RoleAUser delete(RoleAUser input) {
        roleAUserRepository.delete(input);
        return input;
    }

    /**
     * Finds a RoleAUser (role-user association) by its ID.
     *
     * @param id The RoleAUserKey of the RoleAUser object to find.
     * @return The RoleAUser object with the given ID, or null if not found.
     */
    @Override
    public RoleAUser findById(RoleAUserKey id) {
        return roleAUserRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<RoleAUser> findByRoleId(String roleId) {
        return roleAUserRepository.findByRoleId(roleId);
    }

    @Override
    public Long countByRoleId(String roleId) {
        return roleAUserRepository.countByIdRole(roleId);
    }

    /**
     * Deletes a list of RoleAUser (role-user associations) from the database.
     *
     * @param input A list of RoleAUser objects to delete.
     */
    @Override
    public void delete(List<RoleAUser> input) {
        roleAUserRepository.deleteAll(input);
    }

}
