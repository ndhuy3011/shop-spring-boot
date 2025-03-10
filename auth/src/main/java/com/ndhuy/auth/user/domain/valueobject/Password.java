package com.ndhuy.auth.user.domain.valueobject;

import org.springframework.security.crypto.bcrypt.BCrypt;

public record Password(String value) {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 100;
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + MIN_LENGTH + ","
            + MAX_LENGTH + "}$";
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must contain at least one digit, one lowercase letter, one uppercase letter, no whitespace and at least 8 characters";
    public static final String PASSWORD_LENGTH_MESSAGE = "Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String PASSWORD_NULL_MESSAGE = "Password must not be null or empty";
    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(PASSWORD_NULL_MESSAGE);
        }
        if (!value.matches(PASSWORD_PATTERN)) {
            throw new IllegalArgumentException(PASSWORD_PATTERN_MESSAGE);
        }
    }

    public static Password of(String password) {
        return new Password(password);
    }

    private String hashString(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt());
    }

    public String hashPassword(String password) {
        return hashString(password);
    }

    public void hashPassword() {
        BCrypt.hashpw(this.value, BCrypt.gensalt());
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.value);
    }

    public boolean checkPassword(Password password) {
        return BCrypt.checkpw(password.value(), this.value);
    }

}
