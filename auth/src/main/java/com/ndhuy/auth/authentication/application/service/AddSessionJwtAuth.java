package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtOut;

public interface AddSessionJwtAuth {
    AddSessionAuthJwtOut doMain(AddSessionAuthJwtIn cplin);
}
