package com.ndhuy.auth.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;

public interface RoleAUserRepository extends JpaRepository<RoleAUser, RoleAUserKey> {

}
