package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.GetInfoUserDto;
import com.ndhuy.auth.user.application.dto.JwtUserDto;
import com.ndhuy.auth.user.application.dto.LoginUserDto;
import com.ndhuy.auth.user.application.dto.RegisterUserDto;
import com.ndhuy.auth.user.application.service.IUserService;
import com.ndhuy.auth.user.domain.dao.IUserDao;
import com.ndhuy.auth.user.domain.model.User;
import com.ndhuy.auth.user.domain.model.UserDetail;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

    @Resource
    IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        return UserDetail.of(user);
    }

    @Override
    public GetInfoUserDto registerUser(RegisterUserDto userDto) {
        log.info("Register user: {}", userDto);
        var user = userDao.findByUsername(userDto.getUsername());
        if (!Objects.isNull(user)) {
            throw new UsernameNotFoundException("USER_EXIST");
        }
        var userNew = User.of(userDto.getUsername(), userDto.getPassword());
        userDao.insert(userNew);
        return GetInfoUserDto.builder()
                .id(userNew.getId().value())
                .username(userNew.getUsername().value())
                .build();
    }

    @Override
    public JwtUserDto login(LoginUserDto userDto) {
        log.info("Login user: {}", userDto.getUsername());
        var user = userDao.findByUsername(userDto.getUsername());
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        if (!user.getPassword().checkPassword(userDto.getPassword())) {
            throw new UsernameNotFoundException("Password not match");
        }
        return JwtUserDto.builder()
                .jwt("jwt")
                .build();
    }

    @Override
    public GetInfoUserDto getUser(String username) {
        log.info("Get user: {}", username);
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }
        return GetInfoUserDto.builder()
                .id(user.getId().value())
                .username(user.getUsername().value())
                .build();
    }

}
