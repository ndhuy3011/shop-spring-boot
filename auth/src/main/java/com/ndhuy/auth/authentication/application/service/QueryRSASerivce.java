package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.JsonWebKeyDto.JsonWebKeySetOut;

public interface QueryRSASerivce {
        /**
     * Returns the pre-computed JWKS response. This method is optimized for multiple
     * calls.
     *
     * @return The JsonWebKeySetOut containing the JWKS information.
     */
    JsonWebKeySetOut  jwks();
}
