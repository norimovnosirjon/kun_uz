package com.mbs.api.dto.response;

import com.mbs.api.enums.ProfileRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 07.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponseDTO {
    String id;
    String name;
    String surname;
    ProfileRole role;
    String accessToken;
}
