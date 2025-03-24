package com.ndhuy.auth.authentication.domain.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("auth_session_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthSesssionUser {

    @Id
    private String username;

    Set<String> jwtSessionIds;


    public static AuthSesssionUser of(String username){
        return new AuthSesssionUser(username,new HashSet<>());
    }
}
