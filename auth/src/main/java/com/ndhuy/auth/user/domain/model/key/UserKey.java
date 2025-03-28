package com.ndhuy.auth.user.domain.model.key;

import java.util.UUID;

import jakarta.persistence.Embeddable;

/**
 * Represents the unique identifier for a User. This class is an embeddable
 * value object, meaning it will be embedded within another entity (likely the
 * User entity).
 */
@Embeddable
public record UserKey(UUID value) {

    public static final String USER_KEY_NULL = "User key must not be null or empty";

    /**
     * Constructor that validates the provided UUID value.
     *
     * @param value The UUID value for the UserKey.
     * @throws IllegalArgumentException if the provided value is null.
     */
    public UserKey {
        if (value == null) {
            throw new IllegalArgumentException(USER_KEY_NULL);
        }
    }

    /**
     * Default constructor that generates a random UUID. This constructor is used
     * when a new UserKey needs to be created without a specific existing UUID.
     */
    public UserKey() {
        this(UUID.randomUUID());
    }

    /**
     * Constructor that creates a UserKey from a String representation of a UUID.
     *
     * @param value The String representation of the UUID.
     */
    public UserKey(String value) {
        this(UUID.fromString(value));
    }

    /**
     * Creates a UserKey from a String representation of a UUID.
     * This is a static factory method, often preferred over a constructor
     * for better readability.
     *
     * @param value The String representation of the UUID.
     * @return A new UserKey instance.
     */
    public static UserKey fromString(String value) {
        return new UserKey(UUID.fromString(value));
    }
}
