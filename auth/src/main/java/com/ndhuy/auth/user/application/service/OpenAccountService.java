package com.ndhuy.auth.user.application.service;

import com.ndhuy.app.exception.application.runtime.ExistRuntimeException;
import com.ndhuy.auth.user.application.dto.AddAcountDto.OpenAccountIn;
import com.ndhuy.auth.user.application.dto.AddAcountDto.OpenAccountOut;

public interface OpenAccountService {
    /**
     * Opens a new user account.
     *
     * @param userDto The data for opening the user account.
     * @return The result of the account opening operation.
     * @throws ExistRuntimeException if the username already exists.
     */
    OpenAccountOut doMain(OpenAccountIn cplIn);

}
