package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String name;
}
