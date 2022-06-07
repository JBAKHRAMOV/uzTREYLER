package com.company.repository;

import com.company.entity.JanrEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JanrRepository extends JpaRepository<JanrEntity, String> {
}