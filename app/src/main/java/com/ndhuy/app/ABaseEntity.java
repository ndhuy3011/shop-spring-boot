package com.ndhuy.app;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ABaseEntity {
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String lastModifiedBy;

    private String deletedBy;

    private Instant deletedDate;

    private boolean deleted = false;

    @Version
    private Long version;

}
