package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleOut;
import com.ndhuy.auth.user.application.exception.CreateRoleExistException;

public interface AddRoleService {

    /**
     * Executes the main logic to add a new role.
     *
     * @param cpln the role information to be created. Marked with &#64;Valid to
     *             indicate it will be validated.
     * @return CreateRoleOut containing the created role information.
     * @throws CreateRoleExistException If a role with the given ID already exists.
     */
    CreateRoleOut doMain(CreateRoleIn cpln);
}
