package com.ndhuy.auth.authentication.domain.model;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("auth_session")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionAuth {
    @Id
    private String jwtSession;
}
