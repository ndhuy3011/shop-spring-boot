package com.ndhuy.auth.user.domain.dao;

import java.util.List;
import java.util.Optional;

import com.ndhuy.auth.user.domain.model.RoleAUser;
import com.ndhuy.auth.user.domain.model.key.RoleAUserKey;

public interface IRoleAUserDao  extends IBaseDao<RoleAUserKey, RoleAUser>{
        Optional<RoleAUser> findByRoleId(String roleId);
        Long countByRoleId(String roleId);
        void delete(List<RoleAUser> input);
}
