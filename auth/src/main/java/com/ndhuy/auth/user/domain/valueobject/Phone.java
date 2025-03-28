package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the phone number of a user. This record encapsulates the phone
 * number value
 * and ensures its validity.
 */
public record Phone(String value) {
    // Constants for phone number validation
    public static final String REGEX = "^(\\+84|0)\\d{9,10}$"; // Improved regex for Vietnamese phone numbers
    public static final String REGEX_MESSAGE = "Phone number is invalid";
    public static final int MAX_LENGTH = 11;

    /**
     * Constructor that validates the phone number. This is a compact constructor.
     * It enforces the following rules:
     * <ul>
     * <li>The phone number must match the specified regular expression
     * for Vietnamese phone numbers (starting with +84 or 0, followed by 9 or 10
     * digits).</li>
     * <li>The phone number's length must not exceed {@link #MAX_LENGTH}.</li>
     * </ul>
     *
     * @param value The phone number string.
     * @throws IllegalArgumentException if the phone number does not meet the
     *                                  validation criteria.
     */
    public Phone {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Phone number must not be null or empty"); // Added null check
        }
        if (!value.matches(REGEX)) {
            throw new IllegalArgumentException(REGEX_MESSAGE);
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Phone number must be less than " + MAX_LENGTH + " characters");
        }
    }

    /**
     * Static factory method to create a Phone instance. This method provides a
     * more readable way to create Phone objects.
     *
     * @param value The phone number string.
     * @return A new Phone instance.
     * @throws IllegalArgumentException if the phone number is invalid.
     */
    public static Phone of(String value) {
        return new Phone(value);
    }
}
