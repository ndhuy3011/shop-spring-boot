package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionOut;

public interface PermissionService {
    PermissionOut doMain(PermissionIn cplIn);
}
