package com.mbs.api.controller;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.dto.request.base.BaseLanguageNameRequestDTO;
import com.mbs.api.dto.response.base.BaseLanguageNameResponseDTO;
import com.mbs.api.dto.update.BaseLanguageNameUpdateDTO;
import com.mbs.api.service.ArticleTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 'Bilol Tuxtamurodov' on 14.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */

@RestController
@RequestMapping("/api/v1/article-type")
@Tag(name = "Article type Controller API's")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BaseLanguageNameResponseDTO>> create(@RequestBody BaseLanguageNameRequestDTO dto) {
        return ResponseEntity.ok(articleTypeService.create(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Boolean>> update(@PathVariable(name = "id") String id,  @RequestBody BaseLanguageNameUpdateDTO dto) {
        return ResponseEntity.ok(articleTypeService.update(id, dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(articleTypeService.deleteById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BaseLanguageNameResponseDTO>> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(articleTypeService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BaseLanguageNameResponseDTO>>> getAll() {
        return ResponseEntity.ok(articleTypeService.getAll());
    }
}
