package com.mbs.api.entity;

import com.mbs.api.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 26.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@Entity
@Table(name = "attach")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachEntity extends BaseEntity {
    String path;
    String extension;
    @Column(name = "origen_name")
    String origenName;
    Long size;
}
