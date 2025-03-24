package com.ndhuy.auth.authentication.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.authentication.application.dto.RemoveSessionAuthJwtOut;
import com.ndhuy.auth.authentication.application.service.RemoveSessionAuth;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("api/private/auth")
public class AuthenticationRestPrivate {

    @Resource
    RemoveSessionAuth removeSessionAuth;

    @PostMapping("remove-user")
    public ResponseEntity<RemoveSessionAuthJwtOut> postVerifyUser(@RequestHeader(value = "Authorization") String jwt) {
        return ResponseEntity.ok().body(removeSessionAuth.doMain(jwt));
    }
}
