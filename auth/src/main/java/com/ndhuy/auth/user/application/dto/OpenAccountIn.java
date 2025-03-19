package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.authentication.application.dto.PermissionIn;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OpenAccountIn extends PermissionIn {
    @Email
    private String email;
    private String fullName;
    private String phone;
    private String address;

}
