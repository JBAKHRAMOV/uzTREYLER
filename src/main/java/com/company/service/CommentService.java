package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.CommentDTO;
import com.company.entity.CommentEntity;
import com.company.entity.KinoEntity;
import com.company.entity.ProfileEntity;
import com.company.exception.AppForbiddenException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final KinoService kinoService;

    /**
     * PUBLIC
     */

    public CommentDTO create(CommentDTO dto) {
        KinoEntity kinoEntity = kinoService.get(dto.getKinoId());

        if (kinoEntity == null) {
            log.warn("trailer not found! :  {}", dto.getKinoId());
            throw new ItemNotFoundException("trailer not found!");
        }

        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setKinoId(dto.getKinoId());
        entity.setProfileId(EntityDetails.getProfile().getId());

        commentRepository.save(entity);

        return toDTO(entity);
    }

    public Boolean delete(String commentId) {

        ProfileEntity profile = EntityDetails.getProfile();

        CommentEntity entity = get(commentId);

        if (entity == null) {
            log.warn("Comment Not found!");
            throw new ItemNotFoundException("Comment Not found!");
        }

        if (!entity.getProfileId().equals(profile.getId())) {
            log.warn("not acces! : {}", profile);
            throw new AppForbiddenException("not acces!");
        }

        commentRepository.deleteById(commentId);

        return true;
    }

    public PageImpl<CommentDTO> getByKinoId(String kinoId, int page, int size) {

        KinoEntity entity = kinoService.get(kinoId);

        if (entity == null) {
            log.warn("trailer not found! :  {}", kinoId);
            throw new ItemNotFoundException("trailer not found!");
        }
        Pageable pageable = PageRequest.of(page, size);

        Page<CommentEntity> entityPage = commentRepository.findByKinoId(kinoId, pageable);

        List<CommentDTO> commentDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(commentDTOS, pageable, entityPage.getTotalElements());
    }


    public CommentEntity get(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    private CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setContent(entity.getContent());
        dto.setKinoId(entity.getKinoId());
        dto.setProfileId(entity.getProfileId());
        dto.setDislike_count(entity.getDislike_count());
        dto.setLike_count(entity.getLike_count());
        dto.setReplyId(entity.getReplyId());
        return dto;
    }


    /**
     * ADMIN
     */

    public PageImpl<CommentDTO> getByProfileId(String kinoId, int page, int size) {


        if (kinoService.get(kinoId) == null) {
            log.warn("trailer not found! :  {}", kinoId);
            throw new ItemNotFoundException("trailer not found!");
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<CommentEntity> entityPage = commentRepository.findByKinoId(kinoId, pageable);

        List<CommentDTO> commentDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(commentDTOS, pageable, entityPage.getTotalElements());
    }
}

