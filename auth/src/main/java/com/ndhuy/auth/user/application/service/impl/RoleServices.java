package com.ndhuy.auth.user.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.CreateRoleDto;
import com.ndhuy.auth.user.application.dto.GetInfoRoleDto;
import com.ndhuy.auth.user.application.service.IRoleService;
import com.ndhuy.auth.user.domain.dao.IRoleDao;
import com.ndhuy.auth.user.domain.model.Role;

import jakarta.annotation.Resource;

@Service
public class RoleServices implements IRoleService {

    @Resource
    IRoleDao roleDao;

    /**
     * @param role
     */
    @Override
    public void removeRole(String role) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRole'");
    }

    @Override
    public void removeAllRole() {
        throw new UnsupportedOperationException("Unimplemented method 'removeAllRole'");
    }

    /**
     * @param role
     * @return GetInfoRoleDto
     */
    @Override
    public GetInfoRoleDto createRole(CreateRoleDto role) {
        var roleNew = roleDao.insert(Role.of(role));
        return GetInfoRoleDto.builder()
                .id(roleNew.getIdRole().value())
                .name(roleNew.getRoleName())
                .build();
    }

    /**
     * @param role
     * @return GetInfoRoleDto
     */
    @Override
    public GetInfoRoleDto getRole(String role) {
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }

    @Override
    public List<GetInfoRoleDto> getAllRole() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllRole'");
    }

}
