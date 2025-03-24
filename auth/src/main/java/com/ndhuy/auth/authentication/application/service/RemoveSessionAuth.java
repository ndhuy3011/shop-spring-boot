package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.RemoveSessionAuthJwtOut;

public interface RemoveSessionAuth {
    RemoveSessionAuthJwtOut doMain(String jwt);
}
