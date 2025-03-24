package com.ndhuy.auth.authentication.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;

@Repository
public interface AuthSessionJwtRepository extends CrudRepository<AuthSessionJwt, String> {

}
