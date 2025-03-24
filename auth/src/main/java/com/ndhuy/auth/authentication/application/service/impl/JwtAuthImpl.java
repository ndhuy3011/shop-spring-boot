package com.ndhuy.auth.authentication.application.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtAuthImpl implements JwtService {

    @Resource
    JwtEncoder jwtEncoder;
    @Resource
    JwtDecoder jwtDecoder;

    /**
     * Generates a JWT access token and a JWT refresh token for a user.
     *
     * @param cplIn User information used to build the JWT claims (e.g., username,
     *              ID).
     * @return PermissionOut containing the generated JWT access token, refresh
     *         token, issue time, and expiration time.
     */
    @Override
    public PermissionOut generatorJWT(GetInfoUserOut cplIn) {
        log.info(log.getName() + " generator JWT ");
        var issueAt = Instant.now();
        var expiresAt = issueAt.plus(Duration.ofHours(1));
        // Build the claims for the access token. Claims are key-value pairs that
        // represent user attributes.

        var jwtClaim = JwtClaimsSet.builder()
                .id(cplIn.getUsername())
                .claim("uuid", cplIn.getId())
                .issuedAt(issueAt)
                .expiresAt(expiresAt)
                .build();
        // Build the claims for the refresh token. Refresh tokens usually have a longer
        // lifespan.
        var expiresAtRefresh = expiresAt.plus(Duration.ofHours(1));
        var jwtRefreshClaim = JwtClaimsSet.builder()
                .id(cplIn.getUsername())
                .claim("uuid", cplIn.getId())
                .issuedAt(issueAt)
                .expiresAt(expiresAtRefresh)
                .build();

        return PermissionOut.builder()
                .jwt(getTokenValue(jwtClaim))
                .jwtRefresh(getTokenValue(jwtRefreshClaim))
                .issueAt(issueAt.toString())
                .expiresAt(expiresAt.toString()).build();

    }

    /**
     * Encodes the JWT claims into a JWT token string.
     *
     * @param jwtClaimsSet The set of claims to be encoded into the JWT.
     * @return The JWT token string.
     */
    private String getTokenValue(JwtClaimsSet jwtClaimsSet) {
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    /**
     * Parses a JWT token string and returns the claims as a Map.
     *
     * @param token The JWT token string to parse.
     * @return A Map containing the claims from the JWT.
     */
    @Override
    public Map<String, Object> parseJWT(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token string.
     * @return The username of the user to whom the token was issued.
     */
    @Override
    public String getUsername(String token) {
        return jwtDecoder.decode(token).getId();
    }

}
