package com.ndhuy.auth.user.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RoleAUserKey {
    private RoleKey idRole;
    private UserKey idUser;
}
