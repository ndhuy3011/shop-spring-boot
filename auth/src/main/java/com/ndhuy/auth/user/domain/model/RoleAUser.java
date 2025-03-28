package com.ndhuy.auth.user.domain.model;

import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;
import com.ndhuy.auth.user.domain.model.key.RoleKey;
import com.ndhuy.auth.user.domain.model.key.UserKey;

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

    public static RoleAUser of(String roleId,String userId){
        var roleKey = RoleKey.of(roleId);
        var userKey = UserKey.fromString(userId);
        return new RoleAUser(RoleAUserKey.of(roleKey, userKey));
    }

}
