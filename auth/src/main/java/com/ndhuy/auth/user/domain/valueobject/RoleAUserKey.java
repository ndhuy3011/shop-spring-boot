package com.ndhuy.auth.user.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record RoleAUserKey(RoleKey idRole, UserKey idUser) {
  
}
