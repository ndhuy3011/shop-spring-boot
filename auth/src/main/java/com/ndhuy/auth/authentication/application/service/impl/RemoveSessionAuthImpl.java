package com.ndhuy.auth.authentication.application.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ndhuy.app.exception.application.runtime.UnauthorizedRuntimeException;
import com.ndhuy.auth.authentication.application.dto.RemoveSessionDto.RemoveSessionJwtOut;
import com.ndhuy.auth.authentication.application.service.JwtService;
import com.ndhuy.auth.authentication.application.service.RemoveSessionService;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionJwtDao;
import com.ndhuy.auth.authentication.domain.dao.AuthSessionUserDao;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RemoveSessionAuthImpl implements RemoveSessionService {
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
    public RemoveSessionJwtOut doMain(String jwt) {
        log.info("remove session jwt: " + jwt);
        var username = jwtService.getUsername(jwt);
        removeSessionAuth(jwt);
        removeSessionUserAuth(jwt, username);
        return RemoveSessionJwtOut.builder().isDone(true).build();
    }

    /**
     * Deletes JWT session information from storage.
     *
     * @param jwt The JWT to be deleted.
     * @throws UnauthorizedRuntimeException if the JWT is not found.
     */
    private void removeSessionAuth(String jwt) {
        log.info("remove session auth: " + jwt);
        var sessionJwt = sessionAuthJwtDao.findById(jwt);
        if (Objects.isNull(jwt)) {
            throw new UnauthorizedRuntimeException("jwt.not_found");
        }
        sessionAuthJwtDao.delete(sessionJwt);
    }

    /**
     * Deletes the JWT from user session information.
     *
     * @param jwt      The JWT to be deleted.
     * @param username The username of the user associated with the JWT.
     * @throws UnauthorizedRuntimeException if the JWT is not found for the user.
     */
    private void removeSessionUserAuth(String jwt, String username) {
        log.info("remove session user auth: " + jwt + "-" + username);
        var sessionUser = authSessionUserDao.findById(username);
        if (Objects.isNull(sessionUser)) {
            throw new UnauthorizedRuntimeException("jwt.not_found");
        }
        Optional<String> sessionJwt = sessionUser.getJwtSessionIds().stream()
                .filter(jwtId -> jwtId.equals(jwt)) // Filter for the matching JWT.
                .findFirst(); // Get the first found JWT.

        if (sessionJwt.isPresent()) {
            sessionUser.getJwtSessionIds().remove(sessionJwt.get());
        } else {
            throw new UnauthorizedRuntimeException("jwt.not_found");
        }
        authSessionUserDao.update(username, sessionUser);
    }

}
