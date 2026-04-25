package com.mbs.api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.mbs.api.entity.AttachEntity}
 */
@Data
public class AttachDTO {
    String id;
    String url;
    String extension;
    String origenName;
    Long size;
    LocalDateTime createdDate;
    Boolean visible;
}