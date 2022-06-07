package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KinoJanrDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String kinoId;
    private String janrId;
}
