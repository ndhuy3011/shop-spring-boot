package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Username;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    Username username;
    Password password;


  
}
