package com.ndhuy.auth.user.application.dto;

import java.util.UUID;

import com.ndhuy.auth.user.domain.valueobject.Rolename;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class GetInfoAccountDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetInfoRoleOut {
        private String id;
        private Rolename name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class GetInfoUserOut {
        private UUID id;
        private String username;
        private String address;
        private String fullName;
        private String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class GetRoleAUserOut {
        private String username;
        private String role;

    }

}
