package com.ndhuy.auth.authentication.application.dto;

import jakarta.validation.constraints.NotEmpty;
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
public class GetInfoUserSessionOut {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
}
