package com.ndhuy.auth.user.domain.model.key;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record RoleAUserKey(
        @AttributeOverride(name = "value", column = @Column(name = "role_no")) RoleKey idRole,
        @AttributeOverride(name = "value", column = @Column(name = "user_no")) UserKey idUser) {

    public static RoleAUserKey of(RoleKey idRole, UserKey idUser) {
        return new RoleAUserKey(idRole, idUser);
    }

}
