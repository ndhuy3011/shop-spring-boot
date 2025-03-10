package com.ndhuy.auth.user.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.user.application.dto.GetInfoUserDto;
import com.ndhuy.auth.user.application.service.IUserService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/private/user")
public class UserPrivateRest {
    @Resource
    IUserService userService;

    @GetMapping("/info")
    public ResponseEntity<GetInfoUserDto> getInfoUser(@RequestParam String name) {
        var user = userService.getUser(name);
        return ResponseEntity.ok().body(user);
    }

}
