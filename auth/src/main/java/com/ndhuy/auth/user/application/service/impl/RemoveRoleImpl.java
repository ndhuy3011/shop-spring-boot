package com.ndhuy.auth.user.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleOut;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserOut;
import com.ndhuy.auth.user.application.exception.RoleNotFoundException;
import com.ndhuy.auth.user.application.service.QueryRoleService;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.application.service.RemoveRoleService;
import com.ndhuy.auth.user.domain.dao.impl.RoleAUserDao;
import com.ndhuy.auth.user.domain.dao.impl.RoleDao;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;
import com.ndhuy.auth.user.domain.model.key.RoleKey;
import com.ndhuy.auth.user.domain.model.key.UserKey;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RemoveRoleImpl implements RemoveRoleService {

    @Resource
    RoleDao roleDao;
    @Resource
    RoleAUserDao roleAUserDao;
    @Resource
    QueryRoleService queryRoleService;

    @Resource
    QueryUserService queryUserService;

    /**
     * Executes the main logic to remove a role.
     *
     * @param cpln {@link RemoveRoleIn} containing information about the role to be
     *             removed.
     * @return {@link RemoveRoleOut} indicating whether the role was successfully
     *         removed.
     * @throws RoleNotFoundException If the role does not exist or is still assigned
     *                               to users.
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
     * @param cpln {@link RemoveRoleIn} containing information about the role to be
     *             removed.
     * @throws RoleNotFoundException If the role does not exist or is still assigned
     *                               to users.
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
    /**
     * Removes the association between a role and a user.
     *
     * @param cpln {@link RemoveRoleUserIn} containing the IDs of the role and user to disassociate.
     * @return {@link RemoveRoleUserOut} containing the IDs of the role and user that were disassociated.
     * @throws RoleNotFoundException If the role-user association does not exist.  (Note:  The name is slightly misleading, might want to create a new exception)
     */
    @Override
    public RemoveRoleUserOut removeRoleAUser(RemoveRoleUserIn cpln) {
        var roleAUser = roleAUserDao
                .findById(RoleAUserKey.of(RoleKey.of(cpln.getIdRole()), UserKey.fromString(cpln.getIdUser())));

        if (roleAUser == null) {
            throw new RoleNotFoundException();
        }
        roleAUserDao.delete(roleAUser);

        return RemoveRoleUserOut.builder().idRole(cpln.getIdRole()).idUser(cpln.getIdUser()).build();

    }

}
