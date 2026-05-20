package com.mbs.api.service;

import com.mbs.api.config.details.EntityDetails;
import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.ArticleRequestDTO;
import com.mbs.api.dto.response.ArticleResponseDTO;
import com.mbs.api.dto.response.info.IdAndNameDTO;
import com.mbs.api.dto.response.info.IdAndUrlDTO;
import com.mbs.api.entity.ArticleEntity;
import com.mbs.api.entity.AttachEntity;
import com.mbs.api.entity.CategoryEntity;
import com.mbs.api.entity.RegionEntity;
import com.mbs.api.enums.ArticleStatus;
import com.mbs.api.exception.ItemNotFoundException;
import com.mbs.api.repository.ArticleRepository;
import com.mbs.api.repository.AttachRepository;
import com.mbs.api.repository.CategoryRepository;
import com.mbs.api.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final AttachService attachService;
    private final AttachRepository attachRepository;

    public ApiResponse<ArticleResponseDTO> create(ArticleRequestDTO dto) {
        Optional<RegionEntity> optionalRegion = regionRepository.findByIdAndVisibleIsTrue(dto.getRegionId());
        if (optionalRegion.isEmpty()) {
            throw new ItemNotFoundException("Region topilmadi");
        }

        Optional<CategoryEntity> optionalCategory = categoryRepository.findByIdAndVisibleIsTrue(dto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            throw new ItemNotFoundException("Kategoriya topilmadi");
        }

        Optional<AttachEntity> optionalAttach = attachRepository.findByIdAndVisibleIsTrue(dto.getImageId());
        if (optionalAttach.isEmpty()) {
            throw new ItemNotFoundException("Rasm topilmadi");
        }

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setModeratorId(EntityDetails.getProfileId());
        entity.setSharedCount(0L);
        entity.setViewCount(0L);
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        ArticleEntity save = articleRepository.save(entity);
        return ApiResponse.success(toDTO(save));
    }

    public ArticleResponseDTO toDTO(ArticleEntity entity) {
        ArticleResponseDTO dto = new ArticleResponseDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());

        if (Objects.nonNull(entity.getRegion())) {
            dto.setRegion(new IdAndNameDTO(entity.getRegionId(), entity.getRegion().getNameUz()));
        }
        if (Objects.nonNull(entity.getCategory())) {
            dto.setCategory(new IdAndNameDTO(entity.getCategoryId(), entity.getCategory().getNameUz()));
        }

        if (Objects.nonNull(entity.getImageId())) {

            dto.setImage(new IdAndUrlDTO(entity.getImageId(), attachService.openUrl(entity.getImageId())));
        }

        return dto;

    }
}
