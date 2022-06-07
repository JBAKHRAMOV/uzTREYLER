package com.company.service;

import com.company.repository.CommentLikeRepository;
import com.company.repository.KinoLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final KinoLikeRepository kinoLikeRepository;
}
