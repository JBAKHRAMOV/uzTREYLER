package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.kino.KinoDTO;
import com.company.dto.kino.KinoUpdateDTO;
import com.company.entity.KinoEntity;
import com.company.enums.KinoStatus;
import com.company.exception.ItemNotFoundException;
import com.company.repository.KinoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KinoService {

    private final KinoRepository kinoRepository;

    private final AttachService attachService;


    /**
     * ADMIN
     */


    public KinoDTO  trailerUpload(KinoDTO dto){

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

    public KinoDTO update(KinoUpdateDTO dto, String trailerId){

        KinoEntity entity = get(trailerId);

        if(entity == null){
            log.warn("Trailer not found! : {}",EntityDetails.getProfile());
            throw new ItemNotFoundException("Trailer not found!");
        }

        entity.setCountry(dto.getCountry());
        entity.setType(dto.getType());
        entity.setName(dto.getName());
        entity.setCategoryId(dto.getCategoryId());

        kinoRepository.save(entity);

        return toDTO(entity);
    }
    public Boolean delete(String trailerId) {

        KinoEntity entity = get(trailerId);
        if(entity == null){
            log.warn("Trailer not found! : {}",EntityDetails.getProfile());
            throw new ItemNotFoundException("Trailer not found!");
        }

        kinoRepository.updateDeleteDate(trailerId, LocalDateTime.now());

        return true;
    }

    public KinoEntity get(String trailerId) {
        return kinoRepository.findByIdAndDeletedDateIsNull(trailerId).orElse(null);
    }


    /**
     * PUBLIC
     */

    public KinoDTO getById(String trailerId) {

        KinoEntity entity = get(trailerId);

        if(entity == null){
            log.warn("Trailer not found! : {}",EntityDetails.getProfile());
            throw new ItemNotFoundException("Trailer not found!");
        }

        return toDTO(entity);
    }

    public PageImpl<KinoDTO> getAll(int page,int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<KinoEntity> entityPage = kinoRepository.findAllByDeletedDateIsNull(pageable);

        List<KinoDTO> kinoDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(kinoDTOS,pageable,entityPage.getTotalElements());

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

    public PageImpl<KinoDTO> getByCategoryId(int page, int size, String categoryID) {

        Pageable pageable = PageRequest.of(page, size);

        Page<KinoEntity> entityPage = kinoRepository.findByCategoryId(categoryID, pageable);

        List<KinoDTO> kinoDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(kinoDTOS,pageable,entityPage.getTotalElements());
    }
}
