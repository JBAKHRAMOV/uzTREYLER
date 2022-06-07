package com.company.controller;

import com.company.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@Api(tags = "Category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
}
