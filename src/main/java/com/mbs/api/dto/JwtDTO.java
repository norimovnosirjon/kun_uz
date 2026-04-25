package com.mbs.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 02.04.2026
 * @project Lesson_132
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class JwtDTO {
    String username;
    String role;
}
