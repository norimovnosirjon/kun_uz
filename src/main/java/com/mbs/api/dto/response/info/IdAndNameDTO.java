package com.mbs.api.dto.response.info;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class IdAndNameDTO {
    String id;
    String name;
}
