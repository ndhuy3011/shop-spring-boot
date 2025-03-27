package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleOut;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserOut;
import com.ndhuy.auth.user.application.exception.RoleNotFoundException;

public interface RemoveRoleService {

    /**
     * Executes the main logic to remove a role.
     *
     * @param cpln {@link RemoveRoleIn} containing information about the role to
     *             beremoved.
     * @return {@link RemoveRoleOut} indicating whether the role was successfully
     *         removed.
     * @throws RoleNotFoundException If the role does not exist or is still assigned
     *                               to users.
     */
    RemoveRoleOut doMain(RemoveRoleIn cpln);

    /**
     * Removes the association between a role and a user.
     *
     * @param cpln {@link RemoveRoleUserIn} containing the IDs of the role and user
     *             to disassociate.
     * @return {@link RemoveRoleUserOut} containing the IDs of the role and user
     *         that were disassociated.
     * @throws RoleNotFoundException If the role-user association does not exist.
     *                               (Note: The name is slightly misleading, might
     *                               want to create a new exception)
     */
    RemoveRoleUserOut removeRoleAUser(RemoveRoleUserIn cpln);
}
