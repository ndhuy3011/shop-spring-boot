package com.ndhuy.auth.user.application.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ndhuy.auth.user.application.dto.GetRoleDto.GetInfoRoleOut;
import com.ndhuy.auth.user.application.exception.RoleNotFoundException;
import com.ndhuy.auth.user.application.service.QueryRoleService;
import com.ndhuy.auth.user.domain.dao.impl.RoleDao;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueryRoleImpl implements QueryRoleService {

    @Resource
    RoleDao roleDao;

    /**
     * Retrieves role information by role ID.
     *
     * @param cpln The ID of the role to retrieve. Marked with &#64;Valid to
     *             indicate it should be validated.
     * @return GetInfoRoleOut containing the role's ID and name.
     * @throws RoleNotFoundException if the role is not found.
     */
    @Override
    public GetInfoRoleOut getRole(@Valid String cpln) {
        var role = roleDao.findById(cpln);
        if (Objects.isNull(role)) {
            throw new RoleNotFoundException();
        }
        return GetInfoRoleOut.builder()
                .id(role.getIdRole().value())
                .name(role.getNameRole()).build();
    }

}
