package com.ndhuy.auth.user.domain.model.key;

import java.util.UUID;

import jakarta.persistence.Embeddable;
@Embeddable
public record UserKey(UUID value) {
    public static String USER_KEY_NULL = "User key must not be null or empty";
    public UserKey {
        if (value == null ) {
            throw new IllegalArgumentException(USER_KEY_NULL);
        }
    }

    public UserKey() {
        this(UUID.randomUUID());
    }

    public UserKey(String value) {
        this(UUID.fromString(value));
    }
    public static UserKey fromString(String value) {
        return new UserKey(UUID.fromString(value));
    }

}
