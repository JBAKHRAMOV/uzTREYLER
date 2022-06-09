package com.company.service;

import com.company.dto.AttachDTO;
import com.company.dto.KinoDTO;
import com.company.entity.KinoEntity;
import com.company.enums.KinoStatus;
import com.company.repository.KinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class KinoService {

    private final KinoRepository kinoRepository;

    private final AttachService attachService;


    public KinoDTO  trailerUpload( KinoDTO dto){

        KinoEntity entity = new KinoEntity();
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setStatus(KinoStatus.ACTIVE);
        entity.setTranslationLanguage(dto.getTranslationLanguage());
        entity.setType(dto.getType());
        entity.setCategoryId(dto.getCategoryId());
        entity.setPreviewAttachLink(dto.getPreviewAttachLink());
        entity.setVideoLink(dto.getVideoLink());
        entity.setVisible(true);

        kinoRepository.save(entity);

        return toDTO(entity);

    }


    public KinoDTO toDTO(KinoEntity entity){
        KinoDTO dto = new KinoDTO();

        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());
        dto.setCategoryId(entity.getCategoryId());
        dto.setName(entity.getName());
        dto.setPreviewAttachLink(entity.getPreviewAttachLink());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setTranslationLanguage(entity.getTranslationLanguage());
        dto.setVideoLink(entity.getVideoLink());
        dto.setType(entity.getType());

        return dto;
    }

}
