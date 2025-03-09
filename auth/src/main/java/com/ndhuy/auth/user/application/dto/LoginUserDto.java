package com.ndhuy.auth.user.application.dto;

import com.ndhuy.auth.user.domain.valueobject.Password;
import com.ndhuy.auth.user.domain.valueobject.Username;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserDto {
   private Username username;
   private Password password;
}
