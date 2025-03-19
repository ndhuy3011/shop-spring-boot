package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.exception.domain.NotFondUserException;
import com.ndhuy.auth.user.application.dto.OpenAccountIn;
import com.ndhuy.auth.user.application.dto.OpenAccountOut;
import com.ndhuy.auth.user.application.service.OpenAccountService;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.User;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenAccountServiceImpl implements OpenAccountService {

    @Resource
    IUserDao userDao;
    @Resource
    QueryUserService queryUserService;

    /**
     * Open account
     * 
     * @param userDto
     * @return GetInfoUserDto
     */
    @Override
    public OpenAccountOut doMain(OpenAccountIn userDto) {
        log.info("Register user: {}", userDto);
        checkMain(userDto);
        var userNew = User.of(userDto.getUsername(), userDto.getPassword());
        userDao.insert(userNew);
        return OpenAccountOut.builder()
                .uuid(userNew.getId().value())
                .username(userNew.getUsername().value())
                .build();
    }

    /**
     * Verify data befor open account
     * 
     * @param userDto
     */
    public void checkMain(OpenAccountIn userDto) {
        var user = userDao.findByUsername(userDto.getUsername());
        if (!Objects.isNull(user)) {
            throw new NotFondUserException();
        }
    }

}
