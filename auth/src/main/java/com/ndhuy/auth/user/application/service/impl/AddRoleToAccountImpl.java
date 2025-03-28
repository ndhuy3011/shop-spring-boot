package com.ndhuy.auth.user.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountOut;
import com.ndhuy.auth.user.application.service.AddRoleToAccountService;
import com.ndhuy.auth.user.application.service.QueryRoleService;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.impl.RoleAUserDao;
import com.ndhuy.auth.user.domain.model.RoleAUser;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddRoleToAccountImpl implements AddRoleToAccountService {

    @Resource
    QueryUserService queryUserService;
    @Resource
    QueryRoleService roleService;
    @Resource
    RoleAUserDao roleAUserDao;

    /**
     * Adds a role to a user account.
     *
     * @param cpln The input DTO containing the user ID and role ID.
     * @return The output DTO containing the added role ID and user ID.
     */
    @Override
    public AddRoleToAccountOut doMain(AddRoleToAccountIn cpln) {
        log.info("Adding role to account: {}", cpln); // Added logging with parameter
        var user = queryUserService.getUser(cpln.getIdUser());
        var role = roleService.getRole(cpln.getIdRole()); // Corrected: roleService
        var roleAUser = RoleAUser.of(role.getId(), user.getId().toString());
        roleAUserDao.insert(roleAUser);

        return AddRoleToAccountOut.builder()
                .idRole(role.getId())
                .idUser(user.getUsername()) // Changed to username
                .build();
    }

}
