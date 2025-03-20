package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUsernameOut;

public interface AddSessionUsernameAuth {
    AddSessionAuthUsernameOut doMain(AddSessionAuthUsernameIn cplIn);
}
