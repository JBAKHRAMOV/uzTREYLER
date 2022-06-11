package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.CategoryDTO;
import com.company.dto.kino.KinoDTO;
import com.company.service.CategoryService;
import com.company.service.KinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.java.CharacterArrayTypeDescriptor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@Api(tags = "Category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;


    /**
     * ADMIN
     */

    @PostMapping("")
    @ApiOperation(value = "create ", notes = "method for create Category")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDTO> create(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody
                                              CategoryDTO dto) {
        log.info("category create with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(categoryService.create(dto));
    }


    @DeleteMapping("/{categoryId}")
    @ApiOperation(value = "delete ", notes = "method for delete Category")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable("categoryId") String categoryId) {
        log.info("category delete with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(categoryService.delete(categoryId));
    }


    @PutMapping("/{categoryId}")
    @ApiOperation(value = "update ", notes = "method for update Category")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CategoryDTO> update(@PathVariable("categoryId") String categoryId,
                                              @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody
                                              CategoryDTO dto) {
        log.info("category update with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(categoryService.update(dto, categoryId));
    }

    /**
     * PUBLIC
     */

    @GetMapping("")
    @ApiOperation(value = "get All Category ", notes = "method for Category  get all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<PageImpl<CategoryDTO>> get(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("category get all with: {}", EntityDetails.getProfile());

        return ResponseEntity.ok(categoryService.getAll(page, size));
    }


    @GetMapping("/{categoryId}")
    @ApiOperation(value = "get  Category ", notes = "method for Category  get all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<CategoryDTO> get(@PathVariable("categoryId") String categoryId) {
        log.info("category get with: {}", EntityDetails.getProfile());
        return ResponseEntity.ok(categoryService.get(categoryId));
    }


}
