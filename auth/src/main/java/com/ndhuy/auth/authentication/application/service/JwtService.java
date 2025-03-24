package com.ndhuy.auth.authentication.application.service;

import java.util.Map;

import com.ndhuy.auth.authentication.application.dto.PermissionDto.PermissionOut;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;

public interface JwtService {
    /**
     * Generates a JWT access token and a JWT refresh token for a user.
     *
     * @param cplIn User information used to build the JWT claims (e.g., username,
     *              ID).
     * @return PermissionOut containing the generated JWT access token, refresh
     *         token, issue time, and expiration time.
     */
    public PermissionOut generatorJWT(GetInfoUserOut cplIn);

    /**
     * Parses a JWT token string and returns the claims as a Map.
     *
     * @param token The JWT token string to parse.
     * @return A Map containing the claims from the JWT.
     */
    public Map<String, Object> parseJWT(String token);

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token string.
     * @return The username of the user to whom the token was issued.
     */
    public String getUsername(String token);
}
