package com.ndhuy.auth.user.application.service.impl;

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

@Service
public class UserService implements IUserService {

    @Resource
    IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetail.of(user);
    }

    @Override
    public GetInfoUserDto registerUser(RegisterUserDto userDto) {
        var user = User.of(userDto);
        userDao.insert(user);
        return GetInfoUserDto.builder()
                .id(user.getId().value())
                .username(user.getUsername().value())
                .build();
    }

    @Override
    public JwtUserDto login(LoginUserDto userDto) {
        var user = userDao.findByUsername(userDto.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
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
        var user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return GetInfoUserDto.builder()
                .id(user.getId().value())
                .username(user.getUsername().value())
                .build();
    }

}
