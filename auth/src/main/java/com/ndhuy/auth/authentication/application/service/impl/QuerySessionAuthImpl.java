package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.GetInfoUserSessionOut;
import com.ndhuy.auth.authentication.application.dto.GetSesssionAuthOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.QuerySesssionService;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;

import jakarta.annotation.Resource;

@Service
public class QuerySessionAuthImpl implements QuerySesssionService {
    @Resource
    AuthSessionJwtDao sessionAuthDao;

    @Resource
    AuthSessionUserDao authSessionUserDao;

    @Resource
    JwtService jwtService;

    /**
     * Retrieves session authentication information based on the JWT.
     *
     * @param jwt The JWT (JSON Web Token) used to identify the session.
     * @return GetSesssionAuthOut containing the session authentication details
     *         (JWT, expiration time, issue time).
     * @throws NullPointerException if the session authentication data is not found
     *                              for the given JWT.
     */
    @Override
    public GetSesssionAuthOut getSessionAuth(String jwt) {
        var sessionAuth = sessionAuthDao.findById(jwt);

        Objects.requireNonNull(sessionAuth, "Session Authentication is null");

        return GetSesssionAuthOut.builder().jwtSession(jwt).expiresAt(sessionAuth.getExpiresAt())
                .issueAt(sessionAuth.getIssueAt()).build();
    }

    /**
     * Retrieves user information associated with a given JWT.
     *
     * @param jwt The JWT to retrieve user information.
     * @return GetInfoUserSessionOut containing the user's full name and email.
     */
    @Override
    public GetInfoUserSessionOut getUserInfoSession(String jwt) {
        var username = jwtService.getUsername(jwt);

        var userInfo = authSessionUserDao.findById(username);

        return GetInfoUserSessionOut.builder().fullName(userInfo.getFullName()).email(userInfo.getEmail()).build();

    }

}
