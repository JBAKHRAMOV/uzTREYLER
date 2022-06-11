package com.company.dto.kino;

import com.company.enums.KInoType;
import com.company.enums.KinoStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KinoDTO {

    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String name;

    private String videoLink;

    private String previewAttachLink;

    private String country;

    private String translationLanguage;

    private String duration;

    private Boolean visible;

    private KinoStatus status;

    private KInoType type;

    private String categoryId;

    private LocalDateTime publishedDate;

    private LocalDateTime deletedDate;
}
