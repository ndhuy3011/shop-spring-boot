package com.ndhuy.auth.authentication.domain.dao;

import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;
import com.ndhuy.auth.user.domain.dao.IBaseDao;

public interface AuthSessionJwtDao extends IBaseDao<String,AuthSessionJwt>{
 
    AuthSessionJwt findOne(String id);
  
    void delete(String id);
}
