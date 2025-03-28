package com.ndhuy.auth.user.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.AddRoleToAccountOut;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleUserOut;
import com.ndhuy.auth.user.application.service.AddRoleToAccountService;
import com.ndhuy.auth.user.application.service.RemoveRoleService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/private/role/user")
public class RoleAUserRestPrivate {

    @Resource
    AddRoleToAccountService addRoleToAccountService;
    @Resource
    RemoveRoleService removeRoleService;

    @PostMapping("/add")
    public ResponseEntity<AddRoleToAccountOut> postAddRoleToAccount(@Valid  @RequestBody AddRoleToAccountIn cpln) {
        return ResponseEntity.ok().body(addRoleToAccountService.doMain(cpln));
    }

    @PutMapping("/remove")
    public ResponseEntity<RemoveRoleUserOut> putRemoveRoleUser(@Valid @RequestBody RemoveRoleUserIn cpln) {
        return ResponseEntity.ok().body( removeRoleService.removeRoleAUser(cpln));
    }

}
