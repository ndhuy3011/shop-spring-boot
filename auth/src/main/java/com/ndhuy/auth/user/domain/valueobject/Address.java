package com.ndhuy.auth.user.domain.valueobject;

/**
 * Represents the address of a user. This record encapsulates the address value
 * and ensures its validity.
 */
public record Address(String value) {
    // Constants for address length validation
    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 3;
    public static final String LENGTH_MESSAGE = "Address must be between " + MIN_LENGTH + " and " + MAX_LENGTH
            + " characters";
    public static final String NULL_MESSAGE = "Address must not be null or empty";

    /**
     * Constructor that validates the address value. This is a compact constructor.
     *
     * @param value The address string.
     * @throws IllegalArgumentException if the address is null, empty, or
     *                                  does not meet the length constraints.
     */
    public Address {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_MESSAGE);
        }
    }

    /**
     * Static factory method to create an Address instance. This method provides
     * a more readable way to create Address objects.
     *
     * @param value The address string.
     * @return A new Address instance.
     * @throws IllegalArgumentException if the address is invalid.
     */
    public static Address of(String value) {
        return new Address(value);
    }
}
