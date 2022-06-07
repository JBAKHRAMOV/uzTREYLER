package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String content;
    private String replyId;
    private String profileId;
    private String kinoId;
    private Integer like_count;
    private Integer dislike_count;
}
