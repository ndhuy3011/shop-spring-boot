package com.ndhuy.auth.user.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ndhuy.auth.user.application.dto.GetInfoUserOut;

public interface QueryUserService extends UserDetailsService {
    GetInfoUserOut getUser(String username);
}
