package com.ndhuy.auth.authentication.application.service;

import java.util.Map;

import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;

public interface JwtService {
    public PermissionOut generatorJWT(GetInfoUserOut cplIn);
    public Map<String, Object> parseJWT(String token) ;

    public String getUsername(String token);
}
