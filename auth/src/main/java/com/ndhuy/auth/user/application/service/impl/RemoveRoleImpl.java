package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleOut;
import com.ndhuy.auth.user.application.exception.RoleNotFoundException;
import com.ndhuy.auth.user.application.service.RemoveRoleService;
import com.ndhuy.auth.user.domain.dao.impl.RoleAUserDao;
import com.ndhuy.auth.user.domain.dao.impl.RoleDao;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RemoveRoleImpl implements RemoveRoleService {

    @Resource
    RoleDao roleDao;
    @Resource
    RoleAUserDao roleAUserDao;

    /**
     * Executes the main logic to remove a role.
     *
     * @param cpln {@link RemoveRoleIn} containing information about the role to be removed.
     * @return {@link RemoveRoleOut} indicating whether the role was successfully removed.
     * @throws RoleNotFoundException If the role does not exist or is still assigned to users.
     */
    @Override
    public RemoveRoleOut doMain(RemoveRoleIn cpln) {
        log.info("Removing role: {}", cpln); // Added logging
        checkMain(cpln);
        var role = roleDao.findById(cpln.getIdRole());
        roleDao.delete(role);
        return RemoveRoleOut.builder().isDone(true).build();
    }

    /**
     * Checks if the role exists and is not assigned to any users before removal.
     *
     * @param cpln {@link RemoveRoleIn} containing information about the role to be removed.
     * @throws RoleNotFoundException If the role does not exist or is still assigned to users.
     */
    private void checkMain(RemoveRoleIn cpln) {
        var role = roleDao.findById(cpln.getIdRole());
        if (role == null) { // Changed from Objects.isNull(role) to role == null
            throw new RoleNotFoundException(); // Added message
        }

        Long countRoleAUser = roleAUserDao.countByRoleId(cpln.getIdRole()); // Corrected method name
        if (countRoleAUser > 0) {
            throw new RoleNotFoundException(); // Added message
        }
    }

}
