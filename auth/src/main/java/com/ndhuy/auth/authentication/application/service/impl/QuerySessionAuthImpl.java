package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.GetSesssionAuthOut;
import com.ndhuy.auth.authentication.application.service.QuerySesssionAuth;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;

import jakarta.annotation.Resource;

@Service
public class QuerySessionAuthImpl implements QuerySesssionAuth {
    @Resource
    AuthSessionJwtDao sessionAuthDao;

    @Override
    public GetSesssionAuthOut getSessionAuth(String jwt) {
        var sessionAuth = sessionAuthDao.findById(jwt);

        Objects.requireNonNull(sessionAuth, "Session Authentication is null");

        return GetSesssionAuthOut.builder().jwtSession(jwt).expiresAt(sessionAuth.getExpiresAt())
                .issueAt(sessionAuth.getIssueAt()).build();
    }

}
