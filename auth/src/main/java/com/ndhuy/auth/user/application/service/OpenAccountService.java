package com.ndhuy.auth.user.application.service;

import com.ndhuy.auth.user.application.dto.OpenAccountIn;
import com.ndhuy.auth.user.application.dto.OpenAccountOut;

public interface OpenAccountService {

    OpenAccountOut doMain(OpenAccountIn cplIn);

    void checkMain(OpenAccountIn cplIn);
}
