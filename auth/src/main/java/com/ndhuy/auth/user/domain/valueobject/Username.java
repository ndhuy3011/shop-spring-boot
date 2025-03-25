package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the username of a user. This record encapsulates the username
 * value
 * and ensures its validity.
 */
public record Username(String value) {
    // Constants for username validation
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z0-9]+$";
    public static final String PATTERN_MESSAGE = "Username must contain only letters and numbers";
    public static final String LENGTH_MESSAGE = "Username must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Username must not be null or empty";

    /**
     * Constructor that validates the username. This is a compact constructor.
     * It enforces the following rules:
     * <ul>
     * <li>The username must not be null or blank.</li>
     * <li>The username must be between 3 and 50 characters long.</li>
     * <li>The username must contain only letters and numbers.</li>
     * </ul>
     *
     * @param value The username string.
     * @throws IllegalArgumentException if the username does not meet the validation
     *                                  criteria.
     */
    public Username {
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
     * Static factory method to create a Username instance. This method provides a
     * more readable way to create Username objects.
     *
     * @param value The username string.
     * @return A new Username instance.
     * @throws IllegalArgumentException if the username is invalid.
     */
    public static Username of(String value) {
        return new Username(value);
    }
}
