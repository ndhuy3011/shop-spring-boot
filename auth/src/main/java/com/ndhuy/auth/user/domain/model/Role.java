package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.model.key.RoleKey;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "a_role")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "role_no"))
    private RoleKey idRole;

    private String nameRole;

    public static Role of(String idRole, String nameRole) {
        return new Role(new RoleKey(idRole), nameRole);
    }

}
