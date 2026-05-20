package com.mbs.api.service;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.base.BaseLanguageNameRequestDTO;
import com.mbs.api.dto.response.base.BaseLanguageNameResponseDTO;
import com.mbs.api.entity.RegionEntity;
import com.mbs.api.entity.base.BaseLanguageNameEntity;
import com.mbs.api.exception.ItemAlreadyExistsException;
import com.mbs.api.exception.ItemNotFoundException;
import com.mbs.api.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public ApiResponse<BaseLanguageNameResponseDTO> create(BaseLanguageNameRequestDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findByNameUzAndOrderNumberAndVisibleIsTrue(dto.getNameUz(), dto.getOrderNumber());
        if (optional.isPresent()) {
            throw new ItemAlreadyExistsException("Bunday region yoki order number bor");
        }

        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        RegionEntity save = regionRepository.save(entity);
        return ApiResponse.success(BaseLanguageNameResponseDTO.toDTO(save));
    }

    public ApiResponse<BaseLanguageNameResponseDTO> getById(String id) {
        Optional<RegionEntity> optional = regionRepository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Bunday region mavjud emas");
        }
        return ApiResponse.success(BaseLanguageNameResponseDTO.toDTO(optional.get()));
    }

    public ApiResponse<List<BaseLanguageNameResponseDTO>> getAll() {
        List<BaseLanguageNameResponseDTO> list = regionRepository.findAllByVisibleIsTrue()
                .stream().map(BaseLanguageNameResponseDTO::toDTO).toList();
        return ApiResponse.success(list);
    }

    public ApiResponse<Boolean> delete(String id) {
        int result = regionRepository.updateVisible(id, false);
        return ApiResponse.success(result > 0);
    }


}
