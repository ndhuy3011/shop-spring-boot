package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Rolename;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInfoRoleDto {
    String id;
    Rolename name;
}
