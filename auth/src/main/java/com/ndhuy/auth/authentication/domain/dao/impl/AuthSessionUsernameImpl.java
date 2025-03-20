package com.ndhuy.auth.authentication.domain.dao.impl;

import org.springframework.stereotype.Component;

import com.ndhuy.auth.authentication.domain.dao.AuthSessionUsernameDao;
import com.ndhuy.auth.authentication.domain.model.AuthSesssionUsername;
import com.ndhuy.auth.authentication.domain.repository.AuthSessionUsernameRepository;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class AuthSessionUsernameImpl implements AuthSessionUsernameDao {

    @Resource
    AuthSessionUsernameRepository authSessionUsernameRepository;

    
    /** 
     * @param input
     * @return AuthSesssionUsername
     */
    @Override
    public AuthSesssionUsername insert(AuthSesssionUsername input) {
        return authSessionUsernameRepository.save(input);
    }

    @Override
    public AuthSesssionUsername update(String id, AuthSesssionUsername input) {
        var authSession = findById(id);
        authSession.setSetAuthSesssionJwt(input.getSetAuthSesssionJwt());
        return authSessionUsernameRepository.save(authSession);
    }

    @Override
    public AuthSesssionUsername delete(AuthSesssionUsername input) {
        authSessionUsernameRepository.delete(input);
        return input;
    }

    @Override
    public AuthSesssionUsername findById(String id) {
        return authSessionUsernameRepository.findById(id).orElse(null);
    }

}
