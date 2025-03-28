package com.ndhuy.auth.user.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountOut;
import com.ndhuy.auth.user.application.service.AddRoleToAccountService;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("api/private/role/user")
public class RoleAUserRestPrivate {

    @Resource
    AddRoleToAccountService addRoleToAccountService;

    @PostMapping("path")
    public ResponseEntity<AddRoleToAccountOut> postAddRoleToAccount(@RequestBody AddRoleToAccountIn cpln) {

        return ResponseEntity.ok().body(addRoleToAccountService.doMain(cpln));
    }

}
