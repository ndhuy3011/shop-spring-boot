package com.ndhuy.auth.user.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ndhuy.auth.user.application.dto.GetInfoUserDto;
import com.ndhuy.auth.user.application.dto.JwtUserDto;
import com.ndhuy.auth.user.application.dto.LoginUserDto;
import com.ndhuy.auth.user.application.dto.RegisterUserDto;

public interface IUserService extends UserDetailsService{

    GetInfoUserDto registerUser(RegisterUserDto userDto);
    JwtUserDto login(LoginUserDto userDto);
}
