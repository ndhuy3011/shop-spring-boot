package com.ndhuy.auth.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRoleAUser {
    String user;
    String role;
}
