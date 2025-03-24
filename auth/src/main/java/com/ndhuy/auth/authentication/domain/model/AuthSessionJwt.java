package com.ndhuy.auth.authentication.domain.model;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("auth_session_jwt")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthSessionJwt {
    @Id
    private String jwtSession;
    private String refreshSession;
    private String issueAt;
    private String expiresAt;

}
