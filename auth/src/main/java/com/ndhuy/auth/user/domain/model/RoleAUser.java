package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.valueobject.RoleAUserKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "a_role_user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAUser  {
    @EmbeddedId
    private RoleAUserKey id;


}
