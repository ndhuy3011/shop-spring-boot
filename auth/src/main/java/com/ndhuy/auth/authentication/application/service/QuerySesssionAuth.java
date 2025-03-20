package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.GetSesssionAuthOut;

public interface QuerySesssionAuth {
    GetSesssionAuthOut getSessionAuth(String jwt);
}
