package com.ndhuy.auth.user.application.service;

import com.ndhuy.app.exception.application.runtime.NotFoundRuntimeException;
import com.ndhuy.auth.user.application.dto.GetRoleDto.GetInfoRoleOut;

public interface QueryRoleService {
        /**
     * Retrieves role information by role ID.
     *
     * @param cpln The ID of the role to retrieve. Marked with &#64;Valid to
     *             indicate it should be validated.
     * @return GetInfoRoleOut containing the role's ID and name.
     * @throws NotFoundRuntimeException if the role is not found.
     */
    GetInfoRoleOut getRole(String cpln);
}
