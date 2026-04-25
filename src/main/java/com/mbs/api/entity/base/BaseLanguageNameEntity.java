package com.mbs.api.entity.base;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
public class BaseLanguageNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "created_date")
    @CreationTimestamp
    LocalDateTime createdDate;

    Boolean visible = true;

    @Column(name = "order_number")
    Integer orderNumber;
    @Column(name = "name_uz")
    String nameUz;
    @Column(name = "name_ru")
    String nameRu;
    @Column(name = "name_en")
    String nameEn;
}
