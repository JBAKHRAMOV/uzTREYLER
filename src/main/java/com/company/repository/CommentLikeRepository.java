package com.company.repository;

import com.company.entity.CommentLikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {
    Page<CommentLikeEntity> findByProfileId(String id, Pageable pageable);


}