package com.company.dto.request;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KinoLikeRequestDTO {
    private LikeStatus status;
    private String kinoId;
}
