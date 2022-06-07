package com.company.repository;

import com.company.entity.KinoLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoLikeRepository extends JpaRepository<KinoLikeEntity, String> {
}