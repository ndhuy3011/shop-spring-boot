package com.ndhuy.auth.authentication.application.service;

import com.ndhuy.auth.authentication.application.dto.GetSessionDto.GetInfoUserSessionOut;
import com.ndhuy.auth.authentication.application.dto.GetSessionDto.GetSesssionAuthOut;

public interface QuerySesssionService {
    /**
     * Retrieves session authentication information based on the JWT.
     *
     * @param jwt The JWT (JSON Web Token) used to identify the session.
     * @return GetSesssionAuthOut containing the session authentication details
     *         (JWT, expiration time, issue time).
     * @throws NullPointerException if the session authentication data is not found
     *                              for the given JWT.
     */
    GetSesssionAuthOut getSessionAuth(String jwt);

    /**
     * Retrieves user information associated with a given JWT.
     *
     * @param jwt The JWT to retrieve user information.
     * @return GetInfoUserSessionOut containing the user's full name and email.
     */
    GetInfoUserSessionOut getUserInfoSession(String jwt);
}
