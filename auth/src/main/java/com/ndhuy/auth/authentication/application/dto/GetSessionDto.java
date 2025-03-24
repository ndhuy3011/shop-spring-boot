package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class GetSessionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetInfoUserSessionOut {
        @NotEmpty
        private String fullName;
        @NotEmpty
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetSesssionAuthOut {
        private String jwtSession;
        private String issueAt;
        private String expiresAt;
    }

}
