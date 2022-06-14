package com.company.dto.kino;

import com.company.enums.KInoType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KinoSearchDTO {

    private String name;

    private String janrId;

    private String categoryId;

    private KInoType type;

    private Integer year;
}
