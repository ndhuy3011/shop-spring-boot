package com.ndhuy.auth.authentication.application.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtIn;
import com.ndhuy.auth.authentication.application.dto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.AddSessionJwtAuth;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.PermissionService;
import com.ndhuy.auth.exception.domain.PasswordRuntimeException;
import com.ndhuy.auth.user.application.service.QueryUserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Resource
    QueryUserService queryUserService;
    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    JwtService jwtService;

    @Resource
    AddSessionJwtAuth addSessionJwtAuth;

    
    /** 
     * @param cplIn
     * @return PermissionOut
     */
    @Override
    public PermissionOut doMain(PermissionIn cplIn) {
        log.info(log.getName() + " Service Domain");

        var userDetails = queryUserService.loadUserByUsername(cplIn.getUsername());
        if (!passwordEncoder.matches(cplIn.getPassword(), userDetails.getPassword())) {
            throw new PasswordRuntimeException();
        }
        var user = queryUserService.getUser(cplIn.getUsername());
        var jwt = jwtService.generatorJWT(user);
        addSessionJwtAuth.doMain(AddSessionAuthJwtIn
                .builder()
                .jwtSession(jwt.getJwt())
                .expiresAt(jwt.getExpiresAt())
                .issueAt(jwt.getIssueAt())
                .build());
        return jwt;
    }


}
