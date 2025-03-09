package com.ndhuy.auth.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndhuy.auth.user.domain.model.User;
import com.ndhuy.auth.user.domain.valueobject.UserKey;
import com.ndhuy.auth.user.domain.valueobject.Username;

public interface UserRepository extends JpaRepository<User, UserKey> {
    Optional<User> findByUsername(Username username);
    
}
