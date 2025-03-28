package com.ndhuy.auth.user.application.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleIn;
import com.ndhuy.auth.user.application.dto.AddRoleDto.CreateRoleOut;
import com.ndhuy.auth.user.application.dto.GetRoleDto.GetInfoRoleOut;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleIn;
import com.ndhuy.auth.user.application.dto.RemoveRoleDto.RemoveRoleOut;
import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleIn;
import com.ndhuy.auth.user.application.dto.UpdateRoleDto.UpdateRoleOut;
import com.ndhuy.auth.user.application.service.AddRoleService;
import com.ndhuy.auth.user.application.service.QueryRoleService;
import com.ndhuy.auth.user.application.service.RemoveRoleService;
import com.ndhuy.auth.user.application.service.UpdateRoleService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/private/user")
public class RoleRestPrivate {
    @Resource
    AddRoleService addRoleService;
    @Resource
    UpdateRoleService updateRoleService;

    @Resource
    QueryRoleService queryRoleService;

    @Resource
    RemoveRoleService removeRoleService;

    @PostMapping("/create-role")
    public ResponseEntity<CreateRoleOut> postCreateRole(@Valid @RequestBody CreateRoleIn cpln) {
        return ResponseEntity.status(HttpStatus.OK).body(addRoleService.doMain(cpln));
    }

    @PutMapping("/update-role")
    public ResponseEntity<UpdateRoleOut> putUpdateRole(@Valid @RequestBody UpdateRoleIn cpln) {
        return ResponseEntity.ok().body(updateRoleService.doMain(cpln));
    }

    @GetMapping("/info-role")
    public ResponseEntity<GetInfoRoleOut> getInfoRole(@RequestParam String roleNo) {
        return ResponseEntity.ok().body(queryRoleService.getRole(roleNo));
    }

    @DeleteMapping("/remove-role")
    public ResponseEntity<RemoveRoleOut> deleteRemoveRole(@Valid @RequestBody RemoveRoleIn cpln) {
        return ResponseEntity.ok().body(removeRoleService.doMain(cpln));
    }

}
