package com.reymitech.app.transactions.utils;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     * Column for the id of the entity.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * Column for the creation date.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Column for the update date.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
