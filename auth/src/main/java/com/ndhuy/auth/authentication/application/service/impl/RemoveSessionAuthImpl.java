package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.authentication.application.dto.RemoveSessionAuthJwtOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.RemoveSessionAuth;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;
import com.ndhuy.auth.exception.domain.JwtNotFoundException;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RemoveSessionAuthImpl implements RemoveSessionAuth {
    @Resource
    AuthSessionJwtDao sessionAuthJwtDao;

    @Resource
    AuthSessionUserDao authSessionUserDao;

    @Resource
    JwtService jwtService;

    /**
     * Implements the main logic to delete the JWT authentication session.
     *
     * @param jwt The JWT to be deleted.
     * @return RemoveSessionAuthJwtOut indicating the deletion result.
     */
    @Override
    public RemoveSessionAuthJwtOut doMain(String jwt) {
        var username = jwtService.getUsername(jwt);
        removeSessionAuth(jwt);
        removeSessionUserAuth(jwt, username);
        return RemoveSessionAuthJwtOut.builder().isDone(true).build();
    }

    /**
     * Deletes JWT session information from storage.
     *
     * @param jwt The JWT to be deleted.
     * @throws JwtNotFoundException if the JWT is not found.
     */
    private void removeSessionAuth(String jwt) {
        var sessionJwt = sessionAuthJwtDao.findById(jwt);
        if (Objects.isNull(jwt)) {
            throw new JwtNotFoundException();
        }
        sessionAuthJwtDao.delete(sessionJwt);
    }

    /**
     * Deletes the JWT from user session information.
     *
     * @param jwt      The JWT to be deleted.
     * @param username The username of the user associated with the JWT.
     * @throws JwtNotFoundException if the JWT is not found for the user.
     */
    private void removeSessionUserAuth(String jwt, String username) {
        var sessionUser = authSessionUserDao.findById(username);
        if (Objects.isNull(sessionUser)) {
            throw new JwtNotFoundException();
        }
        Optional<String> sessionJwt = sessionUser.getJwtSessionIds().stream()
                .filter(jwtId -> jwtId.equals(jwt)) // Filter for the matching JWT.
                .findFirst(); // Get the first found JWT.

        if (sessionJwt.isPresent()) {
            sessionUser.getJwtSessionIds().remove(sessionJwt.get());
        } else {
            throw new JwtNotFoundException();
        }
        authSessionUserDao.update(username, sessionUser);
    }

}
