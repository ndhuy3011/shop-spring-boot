package com.ndhuy.auth.authentication.domain.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;
import com.ndhuy.auth.authentication.domain.repository.AuthSessionJwtRepository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Transactional
public class  AuthSessionJwtDaoImpl implements AuthSessionJwtDao {

    @Resource
    AuthSessionJwtRepository sessionAuthRepository;

    @Override
    public AuthSessionJwt insert(AuthSessionJwt cplin) {

        return sessionAuthRepository.save(cplin);
    }

    @Override
    public AuthSessionJwt findOne(String id) {
        return sessionAuthRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        var sessionAuth = findOne(id);
        sessionAuthRepository.delete(sessionAuth);
    }

    @Override
    public AuthSessionJwt update(String id, AuthSessionJwt input) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public AuthSessionJwt delete(AuthSessionJwt input) {
        sessionAuthRepository.delete(input);
        return input;
    }

    @Override
    public AuthSessionJwt findById(String id) {
        return sessionAuthRepository.findById(id).orElse(null);
    }

}
