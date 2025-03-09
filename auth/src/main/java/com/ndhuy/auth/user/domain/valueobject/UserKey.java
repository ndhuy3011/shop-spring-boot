package com.ndhuy.auth.user.domain.valueobject;

import java.util.UUID;

public record UserKey(UUID value) {
    public UserKey {
        if (value == null ) {
            throw new IllegalArgumentException("Id User must not be null or empty");
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
