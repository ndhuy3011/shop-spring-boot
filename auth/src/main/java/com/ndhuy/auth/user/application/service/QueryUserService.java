package com.ndhuy.auth.user.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ndhuy.auth.exception.domain.NotFondUserException;
import com.ndhuy.auth.user.application.dto.GetInfoAccountDto.GetInfoUserOut;

public interface QueryUserService extends UserDetailsService {
        /**
     * Retrieves user information by username.
     * If the user is not found, a NotFondUserException is thrown.
     *
     * @param username The username of the user to retrieve.
     * @return GetInfoUserOut containing the user's ID and username.
     * @throws NotFondUserException if the user is not found.
     */
    GetInfoUserOut getUser(String username);
}
