package com.mbs.api.controller;

import com.mbs.api.dto.response.AttachDTO;
import com.mbs.api.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 'Bilol Tuxtamurodov' on 26.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */
@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(attachService.saveToSystem(file));
    }

   @GetMapping("/open/{fileName}")
    public ResponseEntity<Resource> open(@PathVariable String fileName) {
        return attachService.open(fileName);
   }
   @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {

        return attachService.download(fileName);
   }

   @GetMapping("")
    public ResponseEntity<PageImpl<AttachDTO>> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "15") int size
   ) {
        return ResponseEntity.ok(attachService.getAll(page - 1, size));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(attachService.delete(id));
   }
}
