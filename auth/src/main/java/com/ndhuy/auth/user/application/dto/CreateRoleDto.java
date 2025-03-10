package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Rolename;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {

    String id;
    Rolename name;
    
}
