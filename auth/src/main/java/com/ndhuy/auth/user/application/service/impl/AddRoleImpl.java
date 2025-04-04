package com.ndhuy.auth.user.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.app.exception.application.runtime.ExistRuntimeException;
import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleOut;
import com.ndhuy.auth.user.application.service.AddRoleService;
import com.ndhuy.auth.user.domain.dao.impl.RoleDao;
import com.ndhuy.auth.user.domain.model.Role;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddRoleImpl implements AddRoleService {

    @Resource
    RoleDao roleDao;

    /**
     * Executes the main logic to add a new role.
     *
     * @param cpln {@link CreateRoleIn} containing the role information to be
     *             created. Marked with &#64;Valid to indicate it will be validated.
     * @return {@link CreateRoleOut} containing the created role information.
     * @throws CreateRoleExistException If a role with the given ID already exists.
     */
    @Override
    public CreateRoleOut doMain(@Valid CreateRoleIn cpln) {

        checkMain(cpln);

        log.info("add role: " + cpln.toString());
        var role = Role.of(cpln.getId(), cpln.getName());
        roleDao.insert(role);
        return CreateRoleOut.builder()
                .id(role.getIdRole().value())
                .name(role.getNameRole()).build();

    }

    /**
     * Checks if the role exists before creating it.
     *
     * @param cpln {@link CreateRoleIn} containing the role information to be
     *             created.
     * @throws ExistRuntimeException If a role with the given ID already exists.
     */
    private void checkMain(CreateRoleIn cpln) {
        var role = roleDao.findById(cpln.getId());
        if (role != null) {
            throw new ExistRuntimeException("role.exist", cpln.getId());
        }
    }
}
