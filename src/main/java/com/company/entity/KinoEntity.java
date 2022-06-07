package com.company.entity;

import com.company.enums.KInoType;
import com.company.enums.KinoStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "kino")
public class KinoEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String videoLink;
    @Column
    private String previewAttachLink;
    @Column
    private String country;
    @Column
    private String translationLanguage;
    @Column
    private String duration;
    @Column
    private Boolean visible = true;
    @Column
    @Enumerated(EnumType.STRING)
    private KinoStatus status;
    @Column
    @Enumerated(EnumType.STRING)
    private KInoType type;

    @Column(name = "category_id", nullable = false)
    private String categoryId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column
    private LocalDateTime publishedDate;
    @Column
    private LocalDateTime deletedDate;
}
