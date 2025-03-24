package com.ndhuy.auth.authentication.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ndhuy.auth.authentication.domain.model.AuthSesssionUser;

@Repository
public interface AuthSessionUserRepository extends CrudRepository<AuthSesssionUser, String>{
    
}
