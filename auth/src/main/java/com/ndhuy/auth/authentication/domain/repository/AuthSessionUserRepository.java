package com.ndhuy.auth.authentication.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.ndhuy.auth.authentication.domain.model.AuthSesssionUser;

public interface AuthSessionUserRepository extends CrudRepository<AuthSesssionUser, String>{
    
}
