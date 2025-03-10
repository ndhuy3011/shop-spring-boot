package com.ndhuy.auth.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.auth.user.domain.model.Role;
import com.ndhuy.auth.user.domain.valueobject.RoleKey;
import com.ndhuy.auth.user.domain.valueobject.Rolename;


public interface RoleRepository extends JpaRepository<Role, RoleKey> {
    Optional<Role> findByRoleName(Rolename roleName);

}
