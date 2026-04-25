package com.mbs.api.dto.request.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseLanguageNameRequestDTO {
    String nameUz;
    String nameRu;
    String nameEn;
    Integer orderNumber;
}
