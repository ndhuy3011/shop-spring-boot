package com.ndhuy.auth.user.application.service;

public interface IRoleAUserService {
    void addRoletoUser(String username, String role);

    void removeRoleFromUser(String username, String role);

    void removeAllRoleFromUser(String username);
}
