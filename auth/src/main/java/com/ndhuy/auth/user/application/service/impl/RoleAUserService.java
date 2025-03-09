package com.ndhuy.auth.user.application.service.impl;

import com.ndhuy.auth.user.application.service.IRoleAUserService;
import com.ndhuy.auth.user.application.service.IRoleService;
import com.ndhuy.auth.user.application.service.IUserService;
import com.ndhuy.auth.user.domain.dao.impl.RoleAUserDao;
import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.valueobject.RoleAUserKey;
import com.ndhuy.auth.user.domain.valueobject.RoleKey;
import com.ndhuy.auth.user.domain.valueobject.UserKey;

import jakarta.annotation.Resource;

public class RoleAUserService implements IRoleAUserService {

    @Resource
    RoleAUserDao roleAUserDao;

    @Resource
    IRoleService roleService;

    @Resource
    IUserService userService;

    @Override
    public void addRoletoUser(String username, String role) {
        var user = userService.getUser(username);
        var roleInfo = roleService.getRole(role);
        if (user == null || roleInfo == null) {
            throw new IllegalArgumentException("User or Role not found");
        }
        var roleAUser = new RoleAUserKey(RoleKey.of(role), UserKey.fromString(user.getId().toString()));

        roleAUserDao.insert(new RoleAUser(roleAUser));

        
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
