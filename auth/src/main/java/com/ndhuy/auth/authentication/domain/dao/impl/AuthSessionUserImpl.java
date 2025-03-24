package com.ndhuy.auth.authentication.domain.dao.impl;

import org.springframework.stereotype.Component;

import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;
import com.ndhuy.auth.authentication.domain.model.AuthSesssionUser;
import com.ndhuy.auth.authentication.domain.repository.AuthSessionUserRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class AuthSessionUserImpl implements AuthSessionUserDao {

    @Resource
    AuthSessionUserRepository authSessionUserRepository;

    
    /** 
     * @param input
     * @return AuthSesssionUser
     */
    @Override
    public AuthSesssionUser insert(AuthSesssionUser input) {
        return authSessionUserRepository.save(input);
    }

    @Override
    public AuthSesssionUser update(String id, AuthSesssionUser input) {
        var authSession = findById(id);
        authSession.setJwtSessionIds(input.getJwtSessionIds());
        return authSessionUserRepository.save(authSession);
    }

    @Override
    public AuthSesssionUser delete(AuthSesssionUser input) {
        authSessionUserRepository.delete(input);
        return input;
    }

    @Override
    public AuthSesssionUser findById(String id) {
        return authSessionUserRepository.findById(id).orElse(null);
    }

}
