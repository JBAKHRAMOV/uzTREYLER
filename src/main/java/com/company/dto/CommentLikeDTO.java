package com.company.dto;

import com.company.enums.LikeStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentLikeDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LikeStatus status;
    private String profileId;
    private String commentId;
}
