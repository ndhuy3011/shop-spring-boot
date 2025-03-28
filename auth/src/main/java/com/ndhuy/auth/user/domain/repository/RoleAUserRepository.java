package com.ndhuy.auth.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;

public interface RoleAUserRepository extends JpaRepository<RoleAUser, RoleAUserKey> {
    /**
     * Counts the number of role assignments for a given role ID.
     *
     * @param roleId The ID of the role to count assignments for.
     * @return The number of times the role is assigned to users.
     */
    @Query("SELECT count(rau) FROM RoleAUser rau WHERE rau.roleAUserKey.roleId = :roleId")
    Long countByIdRole(@Param("roleId") String roleId);

    /**
     * Find by role no
     *
     * @param roleId The ID of the role to count assignments for.
     * @return RoleAUser
     */
    @Query("SELECT rau from RoleAUser rau WHERE rau.roleAUserKey.roleId = :roleId")
    Optional<RoleAUser> findByRoleId(@Param("roleId") String roleId);

}
