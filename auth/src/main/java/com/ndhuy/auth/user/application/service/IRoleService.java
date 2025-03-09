package com.ndhuy.auth.user.application.service;

import java.util.List;

import com.ndhuy.auth.user.application.dto.CreateRoleDto;
import com.ndhuy.auth.user.application.dto.GetInfoRoleDto;

public interface IRoleService {
    GetInfoRoleDto createRole(CreateRoleDto role);

    GetInfoRoleDto getRole(String role);

    void removeRole(String role);

    void removeAllRole();

    List<GetInfoRoleDto> getAllRole();
}
