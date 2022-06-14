package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.CommentLikeDTO;
import com.company.dto.KinoLikeDTO;
import com.company.dto.LikeCountDTO;
import com.company.dto.request.CommentLikeRequestDTO;
import com.company.dto.request.KinoLikeRequestDTO;
import com.company.entity.CommentLikeEntity;
import com.company.entity.KinoLikeEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.LikeStatus;
import com.company.enums.ProfileRole;
import com.company.exception.AppForbiddenException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CommentLikeRepository;
import com.company.repository.KinoLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final KinoLikeRepository kinoLikeRepository;

    /**
     * Kino Like
     * */

    public KinoLikeDTO createKinoLike(KinoLikeRequestDTO dto) {
        ProfileEntity profile = EntityDetails.getProfile();
        KinoLikeEntity entity = new KinoLikeEntity();
        entity.setProfileId(profile.getId());
        entity.setKinoId(dto.getKinoId());
        entity.setStatus(dto.getStatus());
        kinoLikeRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean deleteKinoLike(String likeId) {
        ProfileEntity profile = EntityDetails.getProfile();
        KinoLikeEntity entity = getKinoLike(likeId);
        if (entity.getProfileId().equals(profile.getId()) || profile.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            kinoLikeRepository.delete(entity);
        }
        throw new AppForbiddenException("Not Access");
    }

    public PageImpl<KinoLikeDTO> getKinoLikeByUserId(int page, int size) {
        ProfileEntity profile = EntityDetails.getProfile();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<KinoLikeEntity> pageList = kinoLikeRepository.findByProfileId(profile.getId(), pageable);
        List<KinoLikeDTO> videoLikeDTOS = new ArrayList<>();
        for (KinoLikeEntity entity : pageList.getContent()) {
            videoLikeDTOS.add(toDTO(entity));
        }
        return new PageImpl<>(videoLikeDTOS, pageable, pageList.getTotalElements());
    }

    public PageImpl<KinoLikeDTO> getLikeKinoByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<KinoLikeEntity> pageList = kinoLikeRepository.findByProfileId(userId, pageable);
        List<KinoLikeDTO> videoLikeDTOS = new ArrayList<>();
        for (KinoLikeEntity entity : pageList.getContent()) {
            videoLikeDTOS.add(toDTO(entity));
        }
        return new PageImpl<>(videoLikeDTOS, pageable, pageList.getTotalElements());

    }

    public KinoLikeEntity getKinoLike(String likeId) {
        return kinoLikeRepository.findById(likeId).orElseThrow(() -> new ItemNotFoundException("Like Not Found"));
    }

    public KinoLikeDTO toDTO(KinoLikeEntity entity) {
        KinoLikeDTO dto = new KinoLikeDTO();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfileId());
        dto.setStatus(entity.getStatus());
        dto.setKinoId(entity.getKinoId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    /**
     * Comment Like
     * */

    public CommentLikeDTO createCommentLike(CommentLikeRequestDTO dto) {
        ProfileEntity profile = EntityDetails.getProfile();
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setProfileId(profile.getId());
        entity.setCommentId(dto.getCommmentId());
        entity.setStatus(dto.getStatus());
        commentLikeRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean deleteCommentLike(String likeId) {
        ProfileEntity profile = EntityDetails.getProfile();
        CommentLikeEntity entity = getCommentLike(likeId);
        if (entity.getProfileId().equals(profile.getId()) || profile.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            commentLikeRepository.delete(entity);
        }
        throw new AppForbiddenException("Not Access");
    }

    public PageImpl<CommentLikeDTO> getCommentLikeByUserId(int page, int size) {
        ProfileEntity profile = EntityDetails.getProfile();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<CommentLikeEntity> pageList = commentLikeRepository.findByProfileId(profile.getId(), pageable);
        List<CommentLikeDTO> videoLikeDTOS = new ArrayList<>();
        for (CommentLikeEntity entity : pageList.getContent()) {
            videoLikeDTOS.add(toDTO(entity));
        }
        return new PageImpl<>(videoLikeDTOS, pageable, pageList.getTotalElements());
    }

    public PageImpl<CommentLikeDTO> getLikeCommentByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<CommentLikeEntity> pageList = commentLikeRepository.findByProfileId(userId, pageable);
        List<CommentLikeDTO> videoLikeDTOS = new ArrayList<>();
        for (CommentLikeEntity entity : pageList.getContent()) {
            videoLikeDTOS.add(toDTO(entity));
        }
        return new PageImpl<>(videoLikeDTOS, pageable, pageList.getTotalElements());

    }

    public CommentLikeEntity getCommentLike(String likeId) {
        return commentLikeRepository.findById(likeId).orElseThrow(() -> new ItemNotFoundException("Like Not Found"));
    }

    public CommentLikeDTO toDTO(CommentLikeEntity entity) {
        CommentLikeDTO dto = new CommentLikeDTO();
        dto.setId(entity.getId());
        dto.setProfileId(entity.getProfileId());
        dto.setStatus(entity.getStatus());
        dto.setCommentId(entity.getCommentId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public LikeCountDTO getLikeCount(String kinoId){
        int likeCount = kinoLikeRepository.countByKinoIdAndStatus(kinoId, LikeStatus.LIKE);
        int dislikeCount = kinoLikeRepository.countByKinoIdAndStatus(kinoId, LikeStatus.DISLIKE);
        LikeCountDTO dto = new LikeCountDTO();
        dto.setLikeCount(likeCount);
        dto.setDislikeCOunt(dislikeCount);
        return dto;
    }
}
