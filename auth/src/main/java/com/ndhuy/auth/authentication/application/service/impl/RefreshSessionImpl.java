package com.ndhuy.auth.authentication.application.service.impl;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.RefrestSessionOut;
import com.ndhuy.auth.authentication.application.service.RefreshSessionService;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;

import jakarta.annotation.Resource;

@Service
public class RefreshSessionImpl implements RefreshSessionService {
    @Resource
    AuthSessionJwtDao sessionAuthDao;

    @Resource
    AuthSessionUserDao authSessionUserDao;

    @Override
    public RefrestSessionOut doMain(String jwt) {
        throw new UnsupportedOperationException("Unimplemented method 'doMain'");
    }

}
