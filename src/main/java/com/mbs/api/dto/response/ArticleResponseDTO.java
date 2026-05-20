package com.mbs.api.dto.response;

import com.mbs.api.dto.response.info.IdAndNameDTO;
import com.mbs.api.dto.response.info.IdAndUrlDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponseDTO {
    String title;
    String description;
    String content;
    IdAndNameDTO region;
    IdAndNameDTO category;
    IdAndUrlDTO image;
    IdAndNameDTO moderator;
    IdAndNameDTO publisher;
    Integer viewCount;
    Integer sharedCount;
    LocalDateTime createdDate;
    LocalDateTime publishedDate;
}
