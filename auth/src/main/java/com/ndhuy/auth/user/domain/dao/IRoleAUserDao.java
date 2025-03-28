package com.ndhuy.auth.user.domain.dao;

import java.util.List;
import java.util.Optional;

import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;

public interface IRoleAUserDao extends IBaseDao<RoleAUserKey, RoleAUser> {
        /**
         * Finds a RoleAUser (role-user association) by its ID.
         *
         * @param id The RoleAUserKey of the RoleAUser object to find.
         * @return The RoleAUser object with the given ID, or null if not found.
         */
        Optional<RoleAUser> findByRoleId(String roleId);

        Long countByRoleId(String roleId);

        /**
         * Deletes a list of RoleAUser (role-user associations) from the database.
         *
         * @param input A list of RoleAUser objects to delete.
         */
        void delete(List<RoleAUser> input);
}
