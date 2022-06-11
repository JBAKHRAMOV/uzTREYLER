package com.company.repository;

import com.company.entity.KinoLikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoLikeRepository extends JpaRepository<KinoLikeEntity, String> {
    Page<KinoLikeEntity> findByProfileId(String id, Pageable pageable);
}