package com.mbs.api.service;

import com.mbs.api.dto.response.AttachDTO;
import com.mbs.api.entity.AttachEntity;
import com.mbs.api.exception.AppBadRequestException;
import com.mbs.api.exception.ItemNotFoundException;
import com.mbs.api.repository.AttachRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 'Bilol Tuxtamurodov' on 26.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */

@Service
@Slf4j
public class AttachService {
    @Value("${attach.upload.folder}")
    private String folderName;
    @Value("${attach.url}")
    private String attachUrl;
    @Autowired
    private AttachRepository attachRepository;

    public AttachDTO saveToSystem(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppBadRequestException("File not found");
        }

        try {
            String pathFolder = getYMDString();
            String extension = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            String id = UUID.randomUUID().toString();

            File folder = new File(folderName + "/" + pathFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            byte [] bytes = file.getBytes();
            Path path = Paths.get(folderName + "/" + pathFolder + "/" + id + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(id + "." + extension);
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            entity.setExtension(extension);
            entity.setOrigenName(file.getOriginalFilename());
            AttachEntity saved = attachRepository.save(entity);

            return toDTO(saved);

        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }

    public Boolean delete(String id ) {
        AttachEntity entity = getEntity(id);
        attachRepository.delete(entity);
        File file = new File(getPath(entity));
        boolean b = false;
        if (file.exists()) {
            b = file.delete();
        }
        return b;
    }

    public PageImpl<AttachDTO> getAll(int page, int size ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AttachEntity> entities = attachRepository.findAll(pageable);
        return new PageImpl<>(entities.stream().map(this::toDTO).toList(), pageable, entities.getTotalElements());
    }

    public AttachEntity getEntity(String fileId) {
        Optional<AttachEntity> optional = attachRepository.findById(fileId);
        if (optional.isEmpty()) {
            log.warn("Attach error : file not found : {}", fileId);
            throw new ItemNotFoundException("Attach not found");
        }
        return optional.get();
    }

    public ResponseEntity<Resource> open(String id) {
        Resource resource = null;
        try {
            AttachEntity entity = getEntity(id);
            Path filePath = Paths.get(getPath(entity)).normalize();

            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("File not found! : " + id);
            }
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    public ResponseEntity<Resource> download(String id) {
        Resource resource = null;
        try {
            AttachEntity entity = getEntity(id);
            Path filePath = Paths.get(getPath(entity)).normalize();
            resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; fileName=\"" + entity.getOrigenName() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not readable! : " + id);
            }
        } catch (IOException e) {
            throw new RuntimeException("File not readable! : " + id);
        }
    }

    private String getYMDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        return year + "/" + month + "/" + day;
    }

    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public String getPath(AttachEntity entity) {
        return folderName + "/" + entity.getPath() + "/" + entity.getId();
    }

    public String openUrl(String fileId) {
        return attachUrl + "/open/" + fileId;
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setOrigenName(entity.getOrigenName());
        dto.setSize(entity.getSize());
        dto.setExtension(entity.getExtension());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        dto.setUrl(openUrl(entity.getId()));
        return dto;
    }
}
