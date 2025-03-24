package com.ndhuy.auth.authentication.application.dto;

import lombok.Builder;

public class RemoveSessionDto {

    @Builder
    public static class RemoveSessionJwtOut {
        boolean isDone;
    }

}
