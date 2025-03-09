package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.GetRoleAUser;

public interface IRoleAUserService {
    GetRoleAUser addRoletoUser(String username, String role);

    void removeRoleFromUser(String username, String role);

    void removeAllRoleFromUser(String username);
}
