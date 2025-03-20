package com.ndhuy.auth.authentication.domain.model;

import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("auth_session_username")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthSesssionUsername {

    @Id
    private String username;

    Set<String> setAuthSesssionJwt;
}
