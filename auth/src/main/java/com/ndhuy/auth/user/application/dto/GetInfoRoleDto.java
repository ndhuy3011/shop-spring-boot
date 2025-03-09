package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Rolename;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInfoRoleDto {
    Rolename name;
}
