package com.company.repository;

import com.company.entity.KinoLikeEntity;
import com.company.enums.LikeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KinoLikeRepository extends JpaRepository<KinoLikeEntity, String> {
    Page<KinoLikeEntity> findByProfileId(String id, Pageable pageable);

    int countByKinoIdAndStatus(String kinoId, LikeStatus status);
}