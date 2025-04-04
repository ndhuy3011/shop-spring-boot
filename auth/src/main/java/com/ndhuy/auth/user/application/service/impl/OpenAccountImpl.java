package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.app.exception.application.runtime.ExistRuntimeException;
import com.ndhuy.auth.user.application.dto.AddAcountDto.OpenAccountIn;
import com.ndhuy.auth.user.application.dto.AddAcountDto.OpenAccountOut;
import com.ndhuy.auth.user.application.service.OpenAccountService;
import com.ndhuy.auth.user.application.service.QueryUserService;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.User;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenAccountImpl implements OpenAccountService {

    @Resource
    IUserDao userDao;
    @Resource
    QueryUserService queryUserService; // Corrected naming convention to queryUserService

    /**
     * Opens a new user account.
     *
     * @param userDto The data for opening the user account.
     * @return The result of the account opening operation.
     * @throws NotFondUserException if the username already exists.
     */
    @Override
    public OpenAccountOut doMain(OpenAccountIn userDto) {
        log.info("Opening account: {}", userDto); // Improved log message
        checkMain(userDto); // Corrected method name to checkMain
        User userNew = User.of(userDto.getUsername(), userDto.getPassword());
        userDao.insert(userNew);
        return OpenAccountOut.builder()
                .uuid(userNew.getId().value())
                .username(userNew.getUsername().value())
                .build();
    }

    /**
     * Verifies the data before opening an account.  Specifically, checks if the username already exists.
     *
     * @param userDto The data for the user account to be opened.
     * @throws ExistRuntimeException if the username already exists in the system.
     */
    private void checkMain(OpenAccountIn userDto) {
        log.info("Check opening account: {}", userDto); 
        User user = userDao.findByUsername(userDto.getUsername());
        if (!Objects.isNull(user)) { 
            throw new ExistRuntimeException("user.exist", userDto.getUsername()); 
        }
    }
}
