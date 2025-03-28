package com.ndhuy.auth.user.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UpdateRoleDto {
     @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class UpdateRoleIn {
        @NotNull
        private String id;
        @NotNull
        private String name;
    }


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class UpdateRoleOut {
        @NotNull
        private String id;
        @NotNull
        private String name;
    }
}
