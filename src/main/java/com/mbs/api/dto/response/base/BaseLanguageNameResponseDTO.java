package com.mbs.api.dto.response.base;

import com.mbs.api.dto.request.base.BaseLanguageNameRequestDTO;
import com.mbs.api.entity.ArticleTypeEntity;
import com.mbs.api.entity.base.BaseLanguageNameEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseLanguageNameResponseDTO extends BaseLanguageNameRequestDTO {
    String id;
    LocalDateTime createdDate;

    public static BaseLanguageNameResponseDTO toDTO(BaseLanguageNameEntity entity) {
        BaseLanguageNameResponseDTO dto = new BaseLanguageNameResponseDTO();
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }
}
