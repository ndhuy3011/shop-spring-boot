package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.RemoveSessionDto.RemoveSessionJwtOut;

public interface RemoveSessionService {
    /**
     * Implements the main logic to delete the JWT authentication session.
     *
     * @param jwt The JWT to be deleted.
     * @return RemoveSessionAuthJwtOut indicating the deletion result.
     */
    RemoveSessionJwtOut doMain(String jwt);
}
