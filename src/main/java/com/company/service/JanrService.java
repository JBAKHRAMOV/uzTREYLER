package com.company.service;

import com.company.dto.JanrDTO;
import com.company.entity.JanrEntity;
import com.company.exception.ItemAlreadyExistsException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.JanrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JanrService {
    private final JanrRepository janrRepository;


    public JanrDTO create(JanrDTO dto) {

        janrRepository.findByName(dto.getName()).orElseThrow(() -> {
            throw new ItemNotFoundException("janr all ready exists!");
        });

        JanrEntity entity = new JanrEntity();

        entity.setName(dto.getName());

        janrRepository.save(entity);

        dto.setId(entity.getId());

        return dto;
    }

    public JanrDTO update(JanrDTO dto, String janrId) {
        JanrEntity entity = janrRepository.findById(janrId).orElseThrow(() -> {
            throw new ItemNotFoundException("Janr not found!");
        });
        janrRepository.findByName(dto.getName()).orElseThrow(() -> {
            throw new ItemAlreadyExistsException("Janr all ready exists!");
        });

        entity.setName(dto.getName());
        janrRepository.save(entity);

        return toDTO(entity);

    }

    public PageImpl<JanrDTO> get(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<JanrEntity> entityPage = janrRepository.findAll(pageable);

        List<JanrDTO> janrDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(janrDTOS, pageable, entityPage.getTotalElements());

    }

    public Boolean delete(String janrId) {
        JanrEntity entity = janrRepository.findById(janrId).orElseThrow(() -> {
            throw new ItemNotFoundException("Janr not found!");
        });

        janrRepository.delete(entity);

        return true;
    }

    public JanrDTO get(String id) {
        return toDTO(janrRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Janr not found!");
        }));
    }

    public JanrDTO toDTO(JanrEntity entity) {
        JanrDTO dto = new JanrDTO();
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }


}
