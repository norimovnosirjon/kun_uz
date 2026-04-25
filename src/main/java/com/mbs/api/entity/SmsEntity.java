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
 * @author 'Bilol Tuxtamurodov' on 15.03.2026
 * @project Lesson_email_sending
 * @contact @BilolTuxtamurodov
 */

@Entity
@Table(name = "sms")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsEntity extends BaseEntity {
    Integer code;
    @Column(name = "email")
    String email;

    Boolean used = false;
}
