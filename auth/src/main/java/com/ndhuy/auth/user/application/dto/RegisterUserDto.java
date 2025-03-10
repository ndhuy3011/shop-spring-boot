package com.ndhuy.auth.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterUserDto extends LoginUserDto{
    String email;
    String fullName;
    String phone;
    String address;

}
