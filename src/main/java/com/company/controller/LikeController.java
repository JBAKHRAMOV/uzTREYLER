package com.company.controller;

import com.company.service.LikeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/like")
@Api(tags = "Like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
}
