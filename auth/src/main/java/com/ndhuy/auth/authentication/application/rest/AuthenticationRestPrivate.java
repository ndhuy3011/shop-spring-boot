package com.ndhuy.auth.authentication.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.authentication.application.dto.GetInfoUserSessionOut;
import com.ndhuy.auth.authentication.application.dto.RemoveSessionAuthJwtOut;
import com.ndhuy.auth.authentication.application.service.QuerySesssionService;
import com.ndhuy.auth.authentication.application.service.RemoveSessionService;

import jakarta.annotation.Resource;


@RestController
@RequestMapping("api/private/auth")
public class AuthenticationRestPrivate {

    @Resource RemoveSessionService removeSessionAuth;

    @Resource QuerySesssionService querySesssionAuth;

    @PostMapping("remove-user")
    public ResponseEntity<RemoveSessionAuthJwtOut> postVerifyUser(@RequestHeader(value = "Authorization") String jwt) {
        return ResponseEntity.ok().body(removeSessionAuth.doMain(jwt));
    }

    @GetMapping("get-user-info")
    public ResponseEntity<GetInfoUserSessionOut> getUserInfo(@RequestHeader(value = "Authorization") String jwt) {
        return ResponseEntity.ok().body(querySesssionAuth.getUserInfoSession(jwt));
    }
    
}
