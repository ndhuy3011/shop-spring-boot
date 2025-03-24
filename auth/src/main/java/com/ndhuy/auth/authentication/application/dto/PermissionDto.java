package com.ndhuy.auth.authentication.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Username;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PermissionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermissionIn {
        @NotNull(message = Username.NULL_MESSAGE)
        @Size(min = Username.MIN_LENGTH, max = Username.MAX_LENGTH, message = Username.LENGTH_MESSAGE)
        @Pattern(regexp = Username.PATTERN, message = Username.PATTERN_MESSAGE)
        private String username;
        @NotNull(message = Password.NULL_MESSAGE)
        @Size(min = Password.MIN_LENGTH, max = Password.MAX_LENGTH, message = Password.LENGTH_MESSAGE)
        @Pattern(regexp = Password.PATTERN, message = Password.PATTERN_MESSAGE)
        private String password;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PermissionOut {
        private String jwt;
        private String jwtRefresh;
        private String issueAt;
        private String expiresAt;
    }

}
