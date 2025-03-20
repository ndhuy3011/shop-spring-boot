package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.application.dto.CreateRoleDto;
import com.ndhuy.auth.user.domain.model.key.RoleKey;
import com.ndhuy.auth.user.domain.valueobject.Rolename;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "a_role")
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "role_no"))
    private RoleKey idRole;
    
    @Enumerated(EnumType.STRING)
    private Rolename roleName;

    
    /** 
     * @param createRole
     * @return Role
     */
    public static Role of(CreateRoleDto createRole) {
        return new Role(RoleKey.of(createRole.getId()), createRole.getName());

    }

}
