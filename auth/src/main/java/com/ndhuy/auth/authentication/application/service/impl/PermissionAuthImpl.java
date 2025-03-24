package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.AddSessionJwtIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionUserIn;
import com.ndhuy.auth.authentication.application.dto.PermissionIn;
import com.ndhuy.auth.authentication.application.dto.PermissionOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.PermissionService;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;
import com.ndhuy.auth.authentication.domain.model.AuthSessionJwt;
import com.ndhuy.auth.authentication.domain.model.AuthSesssionUser;
import com.ndhuy.auth.exception.domain.JwtExistException;
import com.ndhuy.auth.exception.domain.PasswordRuntimeException;
import com.ndhuy.auth.user.application.service.QueryUserService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermissionAuthImpl implements PermissionService {

    @Resource
    QueryUserService queryUserService;
    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    JwtService jwtService;

    @Resource
    AuthSessionJwtDao sessionAuthDao;

    @Resource
    AuthSessionUserDao authSessionUserDao;

    /**
     * Implements user authentication and creates a JWT session.
     *
     * @param cplIn The cplIn contains user login information (username and
     *              password).
     * @return PermissionOut containing JWT information (token, expiration time,
     *         etc.).
     * @throws PasswordRuntimeException if the password is incorrect.
     */
    public PermissionOut doMain(@Valid PermissionIn cplIn) {
        log.info("Permission Auth: " + cplIn.getUsername());
        var permission = generatorUser(cplIn);
        // Add Jwt in Session User
        addSessionUserAuth(AddSessionUserIn.builder()
                .jwtSession(permission.getJwt())
                .username(cplIn.getUsername()).build());

        addSessionJwtAuth(
                AddSessionJwtIn.builder().jwtSession(permission.getJwt()).expiresAt(permission.getExpiresAt())
                        .issueAt(permission.getIssueAt()).jwtRefresh(permission.getJwtRefresh()).build());

        return permission;
    }

    /**
     * Performs user authentication and generates a JWT token upon successful
     * authentication.
     *
     * @param cplIn The cplIn contains user login information (username and
     *              password).
     * @return PermissionOut containing JWT information (token, expiration time,
     *         etc.).
     * @throws PasswordRuntimeException if the password does not match.
     */
    private PermissionOut generatorUser(@Valid PermissionIn cplIn) {
        log.info(" generator user: " + cplIn.getUsername());

        var userDetails = queryUserService.loadUserByUsername(cplIn.getUsername());

        // Check if the password matches.
        if (!passwordEncoder.matches(cplIn.getPassword(), userDetails.getPassword())) {
            throw new PasswordRuntimeException();
        }

        // Get user information.
        var user = queryUserService.getUser(cplIn.getUsername());

        // Generate JWT token.
        var jwt = jwtService.generatorJWT(user);

        return jwt;
    }

    /**
     * Adds a JWT (JSON Web Token) to the session storage (e.g., Redis).
     *
     * @param cplin The input DTO (Data Transfer Object) containing JWT session
     *              information.
     *              It includes the JWT itself, the issue time, and the expiration
     *              time. The @Valid annotation indicates that this DTO is
     *              validated.
     * @throws JwtExistException If a JWT with the same ID already exists. This
     *                           exception is thrown by the checkMain method.
     */
    private void addSessionJwtAuth(@Valid AddSessionJwtIn cplIn) {
        log.info("add session jwt auth: " + cplIn.toString());
        if (Objects.nonNull(sessionAuthDao.findById(cplIn.getJwtSession()))) {
            throw new JwtExistException();
        }

        sessionAuthDao.insert(new AuthSessionJwt(cplIn.getJwtSession(), cplIn.getJwtRefresh(), cplIn.getIssueAt(),
                cplIn.getExpiresAt()));

    }

    /**
     * Adds a JWT session ID to a user's session data. If the user doesn't exist, a
     * new user session is created.
     *
     * @param cplIn The input DTO containing the username and JWT session ID.
     */
    private void addSessionUserAuth(@Valid AddSessionUserIn cplIn) {
        log.info("add session user auth: " + cplIn.toString());

        Objects.requireNonNull(cplIn.getUsername(), "Username not null");
        Objects.requireNonNull(cplIn.getJwtSession(), "JWT not null");

        var sessionUser = authSessionUserDao.findById(cplIn.getUsername());
        if (Objects.isNull(sessionUser)) {

            // Get user information.
            var user = queryUserService.getUser(cplIn.getUsername());
            sessionUser = AuthSesssionUser.of(cplIn.getUsername(), user.getFullName(), user.getEmail());
        }
        var isJWT = sessionUser.getJwtSessionIds().contains(cplIn.getUsername());
        if (isJWT) {
            throw new JwtExistException();
        }

        sessionUser.getJwtSessionIds().add(cplIn.getJwtSession());

        authSessionUserDao.update(cplIn.getUsername(), sessionUser);
    }

}
