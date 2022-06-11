package com.company.repository;

import com.company.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    Page<CommentEntity> findByKinoId(String kinoId, Pageable pageable);
}