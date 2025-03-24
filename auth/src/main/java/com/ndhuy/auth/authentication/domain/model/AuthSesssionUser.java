package com.ndhuy.auth.authentication.domain.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
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
    private String fullName;
    private String email;
    private Set<String> jwtSessionIds = new HashSet<>();
    
    private AuthSesssionUser(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.jwtSessionIds = new HashSet<>();
    }


    public static AuthSesssionUser of(String username) {
        return new AuthSesssionUser(username, "", "");
    }



    public static AuthSesssionUser of(String username, String fullName, String email) {
        return new AuthSesssionUser(username, fullName, email);
    }
}
