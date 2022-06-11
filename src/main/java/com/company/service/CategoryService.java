package com.company.service;

import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.exception.ItemAlreadyExistsException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryDTO create(CategoryDTO dto) {

        CategoryEntity entity1 = categoryRepository.findByName(dto.getName()).orElse(null);
        if (entity1 != null) {
            log.warn("Category all ready exists!");
            throw new ItemAlreadyExistsException("Category all ready exists!");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());

        categoryRepository.save(entity);

        return toDTO(entity);
    }

    public Boolean delete(String categoryId) {

        get(categoryId);

        categoryRepository.deleteById(categoryId);

        return true;
    }

    public CategoryDTO update(CategoryDTO dto,String categoryId){
        CategoryDTO categoryDTO = get(categoryId);

        categoryRepository.updateName(dto.getName(),categoryId);

        categoryDTO.setName(dto.getName());

        return categoryDTO;
    }


    /**
     * PUBLIC
     */

    public PageImpl<CategoryDTO> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<CategoryEntity> entityPage = categoryRepository.findAll(pageable);

        List<CategoryDTO> categoryDTOS = entityPage.stream().map(this::toDTO).toList();

        return new PageImpl<>(categoryDTOS, pageable, entityPage.getTotalElements());
    }

    public CategoryDTO get(String id) {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow(() -> {
            log.warn("Category not found!");
            throw new ItemNotFoundException("Category  not found!");
        });

        return toDTO(entity);
    }

    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
