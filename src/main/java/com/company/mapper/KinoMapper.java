package com.company.mapper;

import com.company.enums.KInoType;
import com.company.enums.KinoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KinoMapper {

    private String id;

    private LocalDateTime createdDate;

    private String name;

    private String videoLink;

    private String previewAttachLink;

    private String country;

    private String translationLanguage;

    private String duration;

    private KInoType type;

    private String categoryId;

    private LocalDateTime publishedDate;
}
