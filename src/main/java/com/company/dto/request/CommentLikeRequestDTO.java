package com.company.dto.request;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeRequestDTO {
    private LikeStatus status;
    private String commmentId;
}
