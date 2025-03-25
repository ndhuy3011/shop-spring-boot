package com.ndhuy.auth.user.domain.dao;

import com.ndhuy.auth.user.domain.model.Role;
import com.ndhuy.auth.user.domain.model.key.RoleKey;

public interface IRoleDao  extends IBaseDao<RoleKey,Role> {
    public Role findById(String id) ;
}
