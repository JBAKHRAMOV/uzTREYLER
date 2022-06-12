package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String name;

    private String surname;

    private String password;

    private String email;

    private LocalDateTime deletedDate;

    private ProfileStatus status;

    private ProfileRole role;

    private  String token;
}
