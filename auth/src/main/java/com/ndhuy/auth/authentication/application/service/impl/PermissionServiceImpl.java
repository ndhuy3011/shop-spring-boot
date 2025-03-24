package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.AddSessionAuthJwtIn;
import com.ndhuy.auth.authentication.application.dto.AddSessionAuthUserIn;
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
public class PermissionServiceImpl implements PermissionService {

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

        var permission = generatorUser(cplIn);

        addSessionJwtAuth(
                AddSessionAuthJwtIn.builder().jwtSession(permission.getJwt()).expiresAt(permission.getExpiresAt())
                        .issueAt(permission.getIssueAt()).username(cplIn.getUsername()).build());

        addSessionUserAuth(AddSessionAuthUserIn.builder()
                .jwtSession(permission.getJwt())
                .username(cplIn.getUsername()).build());

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
        log.info(log.getName() + " Service Domain");

        var userDetails = queryUserService.loadUserByUsername(cplIn.getUsername());

        // Check if the password matches.
        if (!passwordEncoder.matches(cplIn.getPassword(), userDetails.getPassword())) {
            throw new PasswordRuntimeException();
        }

        // Get user information.
        var user = queryUserService.getUser(cplIn.getUsername());

        // Generate JWT token.
        var jwt = jwtService.generatorJWT(user);
        // Store JWT session information.
        addSessionJwtAuth(AddSessionAuthJwtIn
                .builder()
                .jwtSession(jwt.getJwt())
                .expiresAt(jwt.getExpiresAt())
                .issueAt(jwt.getIssueAt())
                .build());
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
    private void addSessionJwtAuth(@Valid AddSessionAuthJwtIn cpilIn) {

        if (Objects.nonNull(sessionAuthDao.findById(cpilIn.getJwtSession()))) {
            throw new JwtExistException();
        }
        sessionAuthDao.insert(new AuthSessionJwt(cpilIn.getJwtSession(), cpilIn.getIssueAt(), cpilIn.getExpiresAt()));

    }

    /**
     * Adds a JWT session ID to a user's session data. If the user doesn't exist, a
     * new user session is created.
     *
     * @param cplIn The input DTO containing the username and JWT session ID.
     */
    private void addSessionUserAuth(@Valid AddSessionAuthUserIn cplIn) {
        Objects.requireNonNull(cplIn.getUsername(), "Username not null");
        Objects.requireNonNull(cplIn.getJwtSession(), "JWT not null");

        var user = authSessionUserDao.findById(cplIn.getUsername());
        if (Objects.isNull(user)) {
            user = AuthSesssionUser.of(cplIn.getUsername());
        }
        var isJWT = user.getJwtSessionIds().contains(cplIn.getUsername());
        if (isJWT) {
            throw new JwtExistException();
        }
        user.getJwtSessionIds().add(cplIn.getJwtSession());

    }

}
