package com.ndhuy.auth.user.application.service;

import com.ndhuy.app.exception.application.runtime.NotChangeRuntimeException;
import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleIn;
import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleOut;

public interface UpdateRoleService {
    /**
     * Updates the name of a role.
     *
     * @param cpln The input DTO containing the role ID and the new role name.
     * @return An UpdateRoleOut DTO containing the updated role ID and name.
     * @throws NotChangeRuntimeException if the new role name is the same as the old role
     *                             name.
     */
    UpdateRoleOut doMain(UpdateRoleIn cpln);
}
