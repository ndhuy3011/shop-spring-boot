package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.application.dto.CreateRoleDto;
import com.ndhuy.auth.user.domain.valueobject.RoleKey;
import com.ndhuy.auth.user.domain.valueobject.Rolename;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private RoleKey idRole;

    private Rolename roleName;

    public static Role of(CreateRoleDto createRole) {
        return new Role(RoleKey.of(createRole.getId()),createRole.getName());

    }

}
