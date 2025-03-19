package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import com.ndhuy.auth.user.application.dto.GetRoleAUser;
import com.ndhuy.auth.user.application.service.IRoleAUserService;
import com.ndhuy.auth.user.application.service.IRoleService;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.impl.RoleAUserDao;
import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;
import com.ndhuy.auth.user.domain.model.key.RoleKey;
import com.ndhuy.auth.user.domain.model.key.UserKey;

import jakarta.annotation.Resource;

public class RoleAUserService implements IRoleAUserService {

    @Resource
    RoleAUserDao roleAUserDao;

    @Resource
    IRoleService roleService;

    @Resource
    QueryUserService queryUserService;

    @Override
    public GetRoleAUser addRoletoUser(String username, String role) {
        var user = queryUserService.getUser(username);
        var roleInfo = roleService.getRole(role);

        Objects.requireNonNull(user, "USER_NOT_FOUND");
        Objects.requireNonNull(roleInfo, "ROLE_NOT_FOUND");

        var roleAUser = new RoleAUserKey(RoleKey.of(role), UserKey.fromString(user.getId().toString()));
        roleAUserDao.insert(new RoleAUser(roleAUser));

        return GetRoleAUser.builder()
                .role(role)
                .username(user.getUsername())
                .build();

    }

    @Override
    public void removeRoleFromUser(String username, String role) {

        throw new UnsupportedOperationException("Unimplemented method 'removeRoleFromUser'");
    }

    @Override
    public void removeAllRoleFromUser(String username) {

        throw new UnsupportedOperationException("Unimplemented method 'removeAllRoleFromUser'");
    }

}
