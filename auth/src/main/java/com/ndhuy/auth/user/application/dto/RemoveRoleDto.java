package com.ndhuy.auth.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class RemoveRoleDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class RemoveRoleIn {
        String idRole;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class RemoveRoleOut {
        boolean isDone;
    }

    public static class RomoveRoleUserIn{

    }
    public static class RomoveRoleUserOut{

    }
}
