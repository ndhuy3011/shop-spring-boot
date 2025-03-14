package com.ndhuy.auth.user.domain.valueobject;

import org.springframework.security.crypto.bcrypt.BCrypt;

public record Password(String value) {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 100;
    public static final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + MIN_LENGTH + ","
            + MAX_LENGTH + "}$";
    public static final String PATTERN_MESSAGE = "Password must contain at least one digit, one lowercase letter, one uppercase letter, no whitespace and at least 8 characters";
    public static final String LENGTH_MESSAGE = "Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Password must not be null or empty";

    public static final void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(PATTERN_MESSAGE);
        }
    }

    public Password {
        validate(value);
    }

    public static Password of(String password) {
        return new Password(password);
    }

    public static Password hashPassword(String password) {
        return new Password(BCrypt.hashpw(password, BCrypt.gensalt()));

    }
    public static Password hashPassword(Password password) {
        return new Password(BCrypt.hashpw(password.value, BCrypt.gensalt()));

    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.value);
    }

    public boolean checkPassword(Password password) {
        return BCrypt.checkpw(password.value(), this.value);
    }

}
