package com.ndhuy.auth.user.application.service.impl;

import org.bouncycastle.util.Strings;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleIn;
import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleOut;
import com.ndhuy.auth.user.application.exception.RoleChangeException;
import com.ndhuy.auth.user.application.service.QueryRoleService;
import com.ndhuy.auth.user.application.service.UpdateRoleService;
import com.ndhuy.auth.user.domain.dao.impl.RoleDao;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateRoleImpl implements UpdateRoleService {

    @Resource
    RoleDao roleDao;

    @Resource 
    QueryRoleService queryRoleService;

    /**
     * Updates the name of a role.
     *
     * @param cpln The input DTO containing the role ID and the new role name.
     * @return An UpdateRoleOut DTO containing the updated role ID and name.
     * @throws RoleChangeException if the new role name is the same as the old role name.
     */
    @Override
    public UpdateRoleOut doMain(UpdateRoleIn cpln) {
        var role = roleDao.findById(cpln.getId()); // Retrieve the role from the database

        // Check if the new role name is the same as the old role name
        if (Strings.constantTimeAreEqual(role.getNameRole(), cpln.getName())) {
            throw new RoleChangeException(); // Throw an exception if they are the same
        }

        role.setNameRole(cpln.getName()); // Update the role name

        roleDao.update(role.getIdRole(), role); // Update the role in the database

        // Build and return the output DTO
        return UpdateRoleOut.builder().id(cpln.getId()).name(cpln.getName()).build();
    }

}
