package com.ndhuy.auth.user.application.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.exception.domain.NotFondUserException;
import com.ndhuy.auth.user.application.dto.GetInfoUserOut;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.UserDetail;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueryUserServiceImpl implements QueryUserService {

    @Resource
    IUserDao userDao;

    /**
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new NotFondUserException();
        }
        return UserDetail.of(user);
    }



    /** Query user, if user equal null return error not found user
     * @param username
     * @return GetInfoUserDto
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

}
