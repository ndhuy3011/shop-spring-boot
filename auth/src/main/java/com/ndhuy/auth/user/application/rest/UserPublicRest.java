package com.ndhuy.auth.user.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.user.application.dto.GetInfoUserDto;
import com.ndhuy.auth.user.application.dto.JwtUserDto;
import com.ndhuy.auth.user.application.dto.LoginUserDto;
import com.ndhuy.auth.user.application.dto.RegisterUserDto;
import com.ndhuy.auth.user.application.service.IUserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/public/user")


public class UserPublicRest {
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtUserDto> postLogin(@RequestBody LoginUserDto entity) {
        var user = userService.login(entity);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/register")
    public ResponseEntity<GetInfoUserDto> postRegister(@RequestBody RegisterUserDto entity) {
        log.info("postRegister");
        var user = userService.registerUser(entity);
        return ResponseEntity.ok().body(user);
    }

}
