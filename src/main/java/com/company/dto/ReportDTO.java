package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String content;
    private String profileId;
}
