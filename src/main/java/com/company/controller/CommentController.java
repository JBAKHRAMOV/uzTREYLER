package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.CommentDTO;
import com.company.service.CommentService;
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
@RequestMapping("/api/v1/comment")
@Api(tags = "Comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;


    /**
     * PUBLIC
     */

    @PostMapping("")
    @ApiOperation(value = "create ", notes = "method for create Comment",
            authorizations = @Authorization("JWT TOKEN"))
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CommentDTO> create(@RequestBody
                                             @io.swagger.v3.oas.annotations.parameters.RequestBody
                                             CommentDTO dto) {
        log.info("comment create with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(commentService.create(dto));
    }



    @DeleteMapping("/{commentId}")
    @ApiOperation(value = "delete ", notes = "method for delete Comment",
            authorizations = @Authorization("JWT TOKEN"))
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Boolean> delete(@PathVariable("commentId")String commentId) {
        log.info("comment delete with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(commentService.delete(commentId));
    }


    @GetMapping("/{kinoId}")
    @ApiOperation(value = "get by kinoID ", notes = "method for get Comment by kinoId",
            authorizations = @Authorization("JWT TOKEN"))
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageImpl<CommentDTO>> getByKinoId(@PathVariable("kinoId") String kinoId,
                                                @RequestParam(value = "page",defaultValue = "0")int page,
                                                @RequestParam(value = "size",defaultValue = "10") int size) {
        log.info("get comment by kinoId comment with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(commentService.getByKinoId(kinoId,page,size));
    }


    /**
     * ADMIN
      */

    @GetMapping("/adm/{profileId}")
    @ApiOperation(value = "get by profileId ", notes = "method for get Comment by profileId",
            authorizations = @Authorization("JWT TOKEN"))
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<CommentDTO>> getByProfileId(@PathVariable("profileId") String profileId,
                                                            @RequestParam(value = "page",defaultValue = "0")int page,
                                                            @RequestParam(value = "size",defaultValue = "10") int size) {
        log.info("get comment by profileId comment with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(commentService.getByProfileId(profileId,page,size));
    }





}
