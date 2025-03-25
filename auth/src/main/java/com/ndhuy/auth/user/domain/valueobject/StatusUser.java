package com.ndhuy.auth.user.domain.valueobject;

public enum StatusUser {
    /**
     * The account is active and can access the system.
     */
    NORMAL("active status"),

    /**
     * The account is locked or inactive and cannot access the system.
     */
    CLOSE("close status");

    private final String description; // Added description field

    StatusUser(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the user status.
     *
     * @return The description of the user status.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name() + " - " + this.description;
    }

}
