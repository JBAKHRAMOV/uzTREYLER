package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.CommentLikeDTO;
import com.company.dto.KinoLikeDTO;
import com.company.dto.request.CommentLikeRequestDTO;
import com.company.dto.request.KinoLikeRequestDTO;
import com.company.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
@Api(tags = "Like")
@RequiredArgsConstructor
@Slf4j
public class LikeController {
    private final LikeService likeService;

    /**
     * Kino like
     */

    @ApiOperation(value = "Create kino like", notes = "Method used for create kino like",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @PostMapping("/kino-like")
    public ResponseEntity<KinoLikeDTO> createKinoLike(@RequestBody KinoLikeRequestDTO dto) {
        log.info("create kino like by userId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.createKinoLike(dto));
    }

    @ApiOperation(value = "Delete Kino Like", notes = "Method used for delete kino like",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @DeleteMapping("/kino-like/delete/{id}")
    public ResponseEntity<Boolean> deleteKinoLike(@PathVariable("id") String likeId) {
        log.info("delete comment like by likeId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.deleteKinoLike(likeId));
    }

    @ApiOperation(value = "Get Kino Like for User", notes = "Method used for get kino like for user",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @GetMapping("/kino-like")
    public ResponseEntity<PageImpl<KinoLikeDTO>> getKinoLikeByUserId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("get kino like : {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.getKinoLikeByUserId(page, size));
    }

    @ApiOperation(value = "Get Kino_like List", notes = "Method used for get kino like list",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @GetMapping("/kino-like/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<KinoLikeDTO>> getLikeKinoListByUserId(@PathVariable("userId") String userId,
                                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("get kino like by userId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.getLikeKinoByUserId(userId, page, size));
    }

    /**
     * Comment Like
     */

    @ApiOperation(value = "Create kino like", notes = "Method used for create kino like",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @PostMapping("/Comment-like")
    public ResponseEntity<CommentLikeDTO> createCommentLike(@RequestBody CommentLikeRequestDTO dto) {
        log.info("create comment like by userId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.createCommentLike(dto));
    }

    @ApiOperation(value = "Delete Comment Like", notes = "Method used for delete Comment like",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @DeleteMapping("/Comment-like/delete/{id}")
    public ResponseEntity<Boolean> deleteCommentLike(@PathVariable("id") String likeId) {
        log.info("delete comment like by userId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.deleteCommentLike(likeId));
    }

    @ApiOperation(value = "Get Comment Like for User", notes = "Method used for get Comment like for user",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @GetMapping("/Comment-like")
    public ResponseEntity<PageImpl<CommentLikeDTO>> getCommentLikeByUserId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("get comment like : {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.getCommentLikeByUserId(page, size));
    }

    @ApiOperation(value = "Get Comment like List", notes = "Method used for get Comment like list",
            nickname = "Bilol",
            authorizations = @Authorization("JWT TOKEN"))
    @GetMapping("/Comment-like/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<CommentLikeDTO>> getLikeCommentListByUserId(@PathVariable("userId") String userId,
                                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                                               @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("get comment like by userId: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(likeService.getLikeCommentByUserId(userId, page, size));
    }

}
