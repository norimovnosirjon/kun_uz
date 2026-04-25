package com.mbs.api.entity;

import com.mbs.api.entity.base.BaseEntity;
import com.mbs.api.enums.ProfileRole;
import com.mbs.api.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * @author 'Bilol Tuxtamurodov' on 07.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Entity
@Table(name = "profile")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileEntity extends BaseEntity {
    /* Profile
    id,name,surname,email,phone,password,status,role,visible,created_date,photo_id*/
    String name;
    String surname;
    String email;
    String phone;
    String password;

    @Enumerated(EnumType.STRING)
    ProfileStatus status;
    @Enumerated(EnumType.STRING)
    ProfileRole role;

    @Column(name = "photo_id")
    String photoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    AttachEntity photo;
}
