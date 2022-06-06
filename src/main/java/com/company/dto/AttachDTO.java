package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {
    private String id;
    private String webViewLink;
    private String webContentLink;

    public AttachDTO(String id, String webContentLink) {
        this.id = id;
        this.webContentLink = webContentLink;
    }
}
