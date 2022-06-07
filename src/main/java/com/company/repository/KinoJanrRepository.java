package com.company.repository;

import com.company.entity.KinoJanrEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoJanrRepository extends JpaRepository<KinoJanrEntity, String> {
}