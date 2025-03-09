package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.RoleKey;
import com.ndhuy.auth.user.domain.valueobject.UserKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserToRoleDto {
    RoleKey role;
    UserKey user;
}
