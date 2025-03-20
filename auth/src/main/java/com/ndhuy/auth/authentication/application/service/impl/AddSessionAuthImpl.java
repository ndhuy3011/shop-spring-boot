package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtOut;
import com.ndhuy.auth.authentication.application.service.AddSessionJwtAuth;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;
import com.ndhuy.auth.exception.domain.JwtExistException;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddSessionAuthImpl implements AddSessionJwtAuth {
    @Resource
    AuthSessionJwtDao sessionAuthDao;

    /**
     * Add Jwt in Redis
     * 
     * @param cplin
     * @return AddSessionAuthOut
     */
    @Override
    public AddSessionAuthJwtOut doMain(AddSessionAuthJwtIn cplin) {
        sessionAuthDao.insert(new AuthSessionJwt(cplin.getJwtSession(), cplin.getIssueAt(), cplin.getExpiresAt()));

        return AddSessionAuthJwtOut.builder()
                .jwtSession(cplin.getJwtSession())
             
                .build();
    }

    /**
     * @param cplIn
     */
    public void checkMain(AddSessionAuthJwtIn cplIn) {
        if (Objects.nonNull(sessionAuthDao.findById(cplIn.getJwtSession()))) {
            throw new JwtExistException();
        }
    }

}
