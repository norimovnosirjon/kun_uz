package com.mbs.api.service;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.base.BaseLanguageNameRequestDTO;
import com.mbs.api.dto.response.base.BaseLanguageNameResponseDTO;
import com.mbs.api.dto.update.BaseLanguageNameUpdateDTO;
import com.mbs.api.entity.ArticleTypeEntity;
import com.mbs.api.exception.ItemAlreadyExistsException;
import com.mbs.api.exception.ItemNotFoundException;
import com.mbs.api.repository.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleTypeService {
    private final ArticleTypeRepository articleTypeRepository;

    public ApiResponse<BaseLanguageNameResponseDTO> create(BaseLanguageNameRequestDTO dto) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findByNameUzAndVisibleIsTrue(dto.getNameUz());

        if (optional.isPresent()) {
            throw new ItemAlreadyExistsException("Bunday artikl tipi bor");
        }

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setOrderNumber(dto.getOrderNumber());
        ArticleTypeEntity saved = articleTypeRepository.save(entity);
        return ApiResponse.success(BaseLanguageNameResponseDTO.toDTO(saved));
    }

    public ApiResponse<Boolean> update(String id, BaseLanguageNameUpdateDTO dto) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findByNameUzAndVisibleIsTrue(dto.getNameUz());

        if (optional.isPresent()) {
            throw new ItemAlreadyExistsException("Bunday artikl tipi bor");
        }

        int i = articleTypeRepository.updateNames(id, dto.getNameUz(), dto.getNameEn(), dto.getNameRu());
        if (i > 0) {
            return ApiResponse.success(true);
        } else {
            throw new ItemNotFoundException("Bunday artikl tipi topilmadi");
        }
     /*   Optional<ArticleTypeEntity> optionalUpdate = articleTypeRepository.findByIdAndVisibleIsTrue(id);
        if (optionalUpdate.isEmpty()) {
            log.warn("Bunday artikl tipi topilmadi id : {}", id);
            throw new ItemNotFoundException("Bunday artikl tipi topilmadi");
        }

        ArticleTypeEntity entity = optionalUpdate.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        articleTypeRepository.save(entity);
        return ApiResponse.success(true);*/
    }

    public ApiResponse<Boolean> deleteById(String id) {
        Optional<ArticleTypeEntity> optionalUpdate = articleTypeRepository.findByIdAndVisibleIsTrue(id);
        if (optionalUpdate.isEmpty()) {
            log.warn("Bunday artikl tipi topilmadi id : {}", id);
            throw new ItemNotFoundException("Bunday artikl tipi topilmadi");
        }
        articleTypeRepository.updateVisible(id, false);
        return ApiResponse.success(true);
    }

    public ApiResponse<BaseLanguageNameResponseDTO> getById(String id) {
        Optional<ArticleTypeEntity> optionalUpdate = articleTypeRepository.findByIdAndVisibleIsTrue(id);
        if (optionalUpdate.isEmpty()) {
            log.warn("Bunday artikl tipi topilmadi id : {}", id);
            throw new ItemNotFoundException("Bunday artikl tipi topilmadi");
        }

        return ApiResponse.success(BaseLanguageNameResponseDTO.toDTO(optionalUpdate.get()));
    }

    public ApiResponse<List<BaseLanguageNameResponseDTO>> getAll() {
        List<ArticleTypeEntity> articleTypeEntities = articleTypeRepository.findAllByVisibleIsTrue();
        return ApiResponse.success(articleTypeEntities.stream().map(BaseLanguageNameResponseDTO::toDTO).toList());
    }
}
