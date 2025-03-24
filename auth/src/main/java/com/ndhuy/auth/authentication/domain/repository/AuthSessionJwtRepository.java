package com.ndhuy.auth.authentication.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;

public interface AuthSessionJwtRepository extends CrudRepository<AuthSessionJwt, String> {

}
