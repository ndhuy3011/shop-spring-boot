package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AddSessionDto {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class AddSessionJwtIn {
        @NotEmpty
        private String jwtSession;
        private String jwtRefresh;
        private String issueAt;
        private String expiresAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddSessionJwtOut {
        private String jwtSession;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class AddSessionUserIn {

        @NotNull
        private String username;
        @NotNull
        private String jwtSession;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddSessionUserOut {
        private String username;
    }

}
