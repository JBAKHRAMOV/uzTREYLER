package com.company.repository;

import com.company.entity.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {
}