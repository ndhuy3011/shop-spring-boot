package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the full name of a user. This record encapsulates the full name
 * value
 * and ensures its validity.
 */
public record Fullname(String value) {
    // Constants for full name validation
    public static final int MAX_LENGTH = 50;
    public static final int MIN_LENGTH = 3;
    public static final String PATTERN = "^[a-zA-Z ]+$";
    public static final String PATTERN_MESSAGE = "Fullname must contain only letters and spaces";
    public static final String LENGTH_MESSAGE = "Fullname must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";

    /**
     * Constructor that validates the full name value. This is a compact
     * constructor.
     *
     * @param value The full name string.
     * @throws IllegalArgumentException if the full name is null, empty, does not
     *                                  meet
     *                                  the length constraints, or does not match
     *                                  the required pattern (only letters and
     *                                  spaces).
     */
    public Fullname {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Fullname must not be null or empty");
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException(PATTERN_MESSAGE);
        }
    }

    /**
     * Static factory method to create a Fullname instance. This method provides a
     * more readable way to create Fullname objects.
     *
     * @param value The full name string.
     * @return A new Fullname instance.
     * @throws IllegalArgumentException if the full name is invalid.
     */
    public static Fullname of(String value) {
        return new Fullname(value);
    }
}
