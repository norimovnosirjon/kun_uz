package com.mbs.api.controller;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.base.BaseLanguageNameRequestDTO;
import com.mbs.api.dto.response.base.BaseLanguageNameResponseDTO;
import com.mbs.api.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 'Bilol Tuxtamurodov' on 20.05.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/create")
    @Operation(summary = "Region qo'shish uchun", description = "Extiyot bo'lib qo'shing")
    public ResponseEntity<ApiResponse<BaseLanguageNameResponseDTO>> create(@RequestBody BaseLanguageNameRequestDTO dto) {
        return ResponseEntity.ok(regionService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BaseLanguageNameResponseDTO>> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(regionService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BaseLanguageNameResponseDTO>>> getAll() {
        return ResponseEntity.ok(regionService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(regionService.delete(id));
    }

}
