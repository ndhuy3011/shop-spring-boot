package com.ndhuy.auth.user.domain.model.key;

import jakarta.persistence.Embeddable;

@Embeddable
public record RoleKey(String value) {
    public RoleKey {
        if (value == null ) {
            throw new IllegalArgumentException("role key must not be null or empty");
        }
    }
    
    /** 
     * @param value
     * @return RoleKey
     */
    public static RoleKey of(String value) {
        return new RoleKey(value);
    }
}
