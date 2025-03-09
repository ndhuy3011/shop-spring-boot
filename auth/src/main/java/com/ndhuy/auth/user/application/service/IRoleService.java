package com.ndhuy.auth.user.application.service;

import java.util.List;

import com.ndhuy.auth.user.domain.model.Role;

public interface IRoleService {
    Role addRole(String role);
    Role getRole(String role);
    void removeRole(String role);
    void removeAllRole();
    List<Role> getAllRole();
}
