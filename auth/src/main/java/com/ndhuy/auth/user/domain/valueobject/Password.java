package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the password of a user. This record encapsulates the password
 * value
 * and ensures its validity by enforcing strong password policies.
 */
public record Password(String value) {
    // Constants for password validation
    public static final int MIN_LENGTH = 8;
    public static final int MAX_LENGTH = 100;
    public static final String PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + MIN_LENGTH + ","
            + MAX_LENGTH + "}$";
    public static final String PATTERN_MESSAGE = "Password must contain at least one digit, one lowercase letter, one uppercase letter, no whitespace and at least 8 characters";
    public static final String LENGTH_MESSAGE = "Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Password must not be null or empty";

    /**
     * Constructor that validates the password value. This is a compact constructor.
     * It enforces several rules for password strength:
     * <ul>
     * <li>Must not be null or blank</li>
     * <li>Must be between 8 and 100 characters long</li>
     * <li>Must contain at least one digit</li>
     * <li>Must contain at least one lowercase letter</li>
     * <li>Must contain at least one uppercase letter</li>
     * <li>Must not contain any whitespace</li>
     * </ul>
     *
     * @param value The password string.
     * @throws IllegalArgumentException if the password does not meet the validation
     *                                  criteria.
     */
    public Password {
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

    /**
     * Static factory method to create a Password instance. This method provides a
     * more readable way to create Password objects.
     *
     * @param password The password string.
     * @return A new Password instance.
     */
    public static Password of(String password) {
        return new Password(password);
    }
}
