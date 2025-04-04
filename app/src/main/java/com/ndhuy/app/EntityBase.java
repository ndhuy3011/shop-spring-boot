package com.ndhuy.app;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityBase {
    @Getter
    @Setter
    @CreatedDate
    private Instant createdDate;
    @Getter
    @Setter
    @LastModifiedDate
    private Instant lastModifiedDate;
    @Getter
    @Setter
    @CreatedBy
    private String createdBy;
    @Getter
    @Setter
    @LastModifiedBy
    private String lastModifiedBy;

    private boolean deleted = false;
    @Getter
    @Setter
    @Version
    private Long version;


    public void delete(){
        this.deleted = true;
    }
    public boolean isDeleted() {
        return deleted;
    }

}
