package com.company.service;

import com.company.dto.KinoJanrDTO;
import com.company.entity.KinoEntity;
import com.company.entity.KinoJanrEntity;
import com.company.exception.ItemNotFoundException;
import com.company.repository.KinoJanrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.company.service.AuthService.toDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class KinoJanrService {

    private final KinoJanrRepository kinoJanrRepository;

    private final KinoService kinoService;

    private final JanrService janrService;

    public KinoJanrDTO create(KinoJanrDTO dto) {

        KinoEntity kinoEntity = kinoService.get(dto.getKinoId());

        if (kinoEntity == null) {
            log.warn("kino not found! : {}", dto.getKinoId());
            throw new ItemNotFoundException("kino not found!");
        }

        janrService.get(dto.getJanrId());

        KinoJanrEntity entity =new KinoJanrEntity();
        entity.setJanrId(dto.getJanrId());
        entity.setKinoId(dto.getKinoId());

        kinoJanrRepository.save(entity);

        return toDTO(entity);
    }

    public KinoJanrDTO update(KinoJanrDTO dto,String id){

        KinoEntity kinoEntity = kinoService.get(dto.getKinoId());

        if (kinoEntity == null) {
            log.warn("kino not found! : {}", dto.getKinoId());
            throw new ItemNotFoundException("kino not found!");
        }


        KinoJanrEntity kinoJanrEntity = kinoJanrRepository.findById(id).orElseThrow(() -> {

            log.warn("Kino janr not found! : {}", id);

            throw new ItemNotFoundException("Kino janr not found!");
        });




        kinoJanrEntity.setKinoId(dto.getKinoId());
        kinoJanrEntity.setJanrId(dto.getJanrId());

        kinoJanrRepository.save(kinoJanrEntity);

        return toDTO(kinoJanrEntity);
    }

    public KinoJanrDTO toDTO(KinoJanrEntity entity){
        KinoJanrDTO dto = new KinoJanrDTO();
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setJanrId(entity.getJanrId());
        dto.setKinoId(entity.getKinoId());

        return dto;
    }
}
