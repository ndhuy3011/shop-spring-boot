package com.ndhuy.auth.authentication.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.ndhuy.auth.authentication.domain.model.AuthSesssionUsername;

public interface AuthSessionUsernameRepository extends CrudRepository<AuthSesssionUsername, String>{
    
}
