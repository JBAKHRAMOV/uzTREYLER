package com.company.controller;

import com.company.service.CommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@Api(tags = "Comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
}
