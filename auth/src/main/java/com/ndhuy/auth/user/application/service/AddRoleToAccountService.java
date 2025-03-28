package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountOut;

public interface AddRoleToAccountService {

    /**
     * Adds a role to a user account.
     *
     * @param cpln The input DTO containing the user ID and role ID.
     * @return The output DTO containing the added role ID and user ID.
     */
    AddRoleToAccountOut doMain(AddRoleToAccountIn cpln);
} 
