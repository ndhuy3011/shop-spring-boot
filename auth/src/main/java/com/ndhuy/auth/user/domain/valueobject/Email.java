package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the email address of a user. This record encapsulates the email
 * value
 * and ensures its validity.
 */
public record Email(String value) {
    // Constants for email validation
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    public static final String PATTERN_MESSAGE = "Email must be valid";
    public static final String LENGTH_MESSAGE = "Email must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";

    /**
     * Constructor that validates the email value. This is a compact constructor.
     *
     * @param value The email string.
     * @throws IllegalArgumentException if the email is null, empty, does not meet
     *                                  the length constraints, or does not match
     *                                  the required pattern.
     */
    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(PATTERN_MESSAGE);
        }
    }

    /**
     * Static factory method to create an Email instance. This method provides a
     * more readable way to create Email objects.
     *
     * @param value The email string.
     * @return A new Email instance.
     * @throws IllegalArgumentException if the email is invalid.
     */
    public static Email of(String value) {
        return new Email(value);
    }
}
