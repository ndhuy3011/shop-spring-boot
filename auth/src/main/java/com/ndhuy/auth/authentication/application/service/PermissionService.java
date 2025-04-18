package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.app.exception.application.runtime.UnauthorizedRuntimeException;
import com.ndhuy.auth.authentication.application.dto.PermissionDto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionDto.PermissionOut;

public interface PermissionService {

        /**
     * Implements user authentication and creates a JWT session.
     *
     * @param cplIn contains user login information (username and password).
     * @return PermissionOut containing JWT information (token, expiration time, etc.).
     * @throws UnauthorizedRuntimeException if the password is incorrect.
     */
    PermissionOut doMain(PermissionIn cplIn);
}
