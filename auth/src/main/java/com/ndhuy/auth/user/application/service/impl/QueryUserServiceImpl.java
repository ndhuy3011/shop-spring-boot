package com.ndhuy.auth.user.application.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.exception.domain.NotFondUserException;
import com.ndhuy.auth.user.application.dto.GetInfoAccountDto.GetInfoUserOut;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.UserDetail;
import com.ndhuy.auth.user.domain.model.key.UserKey;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueryUserServiceImpl implements QueryUserService {

    @Resource
    IUserDao userDao;

    /**
     * Loads user details by username for Spring Security authentication.
     *
     * @param username The username of the user to load.
     * @return UserDetails representing the user's details.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new NotFondUserException();
        }
        return UserDetail.of(user);
    }

    /**
     * Retrieves user information by username.
     * If the user is not found, a NotFondUserException is thrown.
     *
     * @param username The username of the user to retrieve.
     * @return GetInfoUserOut containing the user's ID and username.
     * @throws NotFondUserException if the user is not found.
     */
    @Override
    public GetInfoUserOut getUser(String username) {
        log.info("Get user: {}", username);
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new NotFondUserException();
        }
        return GetInfoUserOut.builder()
                .id(user.getId().value())
                .username(user.getUsername().value())
                .build();
    }

    /**
     * Retrieves user information by userkey.
     * If the user is not found, a NotFondUserException is thrown.
     *
     * @param userkey The userkey of the user to retrieve.
     * @return GetInfoUserOut containing the user's ID and userkey.
     * @throws NotFondUserException if the user is not found.
     */
    @Override
    public GetInfoUserOut getUserByKey(String userKey) {
        log.info("Get user: {}", userKey);
        var user = userDao.findById(UserKey.fromString(userKey));
        if (user == null) {
            throw new NotFondUserException();
        }
        return GetInfoUserOut.builder()
                .id(user.getId().value())
                .username(user.getUsername().value())
                .build();
    }

}
