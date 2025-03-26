package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleOut;

public interface RemoveRoleService {
    RemoveRoleOut doMain(RemoveRoleIn cpln);
}
