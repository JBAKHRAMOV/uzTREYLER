package com.company.repository;

import com.company.entity.KinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KinoRepository extends JpaRepository<KinoEntity, String> {
}