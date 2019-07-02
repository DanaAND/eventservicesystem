package com.sda.lukaapp.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.categories.domain.Category;
import com.sda.lukaapp.repository.CategoryRepository;
import com.sda.lukaapp.categories.rest.dto.CategoryMapper;
import com.sda.lukaapp.categories.rest.dto.CategoryResponse;
import com.sda.lukaapp.categories.rest.dto.CreateCategoryRequest;
import com.sda.lukaapp.categories.rest.dto.UpdateCategoryRequest;
import com.sda.lukaapp.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sda.lukaapp.exception.NotFoundException.ErrorCode.CATEGORY_NOT_FOUND;

@Service
public class CategoryService {
    private final Logger logger = LoggerFactory.getLogger((CategoryService.class));
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ObjectMapper jacksonObjectMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }
    public List<CategoryResponse> findAll(){
        logger.debug("finding all categories");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
    public CategoryResponse findById(long id){
        logger.debug("finding by id: {}", id);
        return categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        CATEGORY_NOT_FOUND, "category not found")));
    }

    public CategoryResponse save(CreateCategoryRequest createCategoryRequest) {
        logger.debug("saving user: {}", createCategoryRequest);
        // validate category details
        // convert category request (DTO) to category (ENTITY)
        Category category = categoryMapper.toEntity(createCategoryRequest);
        // save user in db using repository
        Category newCategory = categoryRepository.save(category);
        // Hibernate: insert into category (id, lifeevent, socialevent) values (null, ?, ?)
        // convert back to response (DTO)
        return categoryMapper.toDto(newCategory);
    }

    public CategoryResponse update(UpdateCategoryRequest request, long id) {
        logger.debug("updating category with id: {} and request body: {}", id, request);
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        CATEGORY_NOT_FOUND, "category not found"));
        // validate category details
        categoryMapper.map(request, categoryToUpdate);
        // save category in db using repository
        Category updateCategory = categoryRepository.save(categoryToUpdate);
        // Hibernate: insert into category (id, lifeevent, socialevent) values (null, ?, ?)
        // convert back to response (DTO)
        return categoryMapper.toDto(updateCategory);
    }

    public CategoryResponse partialUpdate(Map<String, Object> updates, long id) {
        logger.debug("patching category with id: {} and request body: {}", id, updates);
        // De-serialise request body into a DTO
        // Run some sort of validation
        // Load entity being updated
        // Copy fields that change over to the entity with the help of a Model Mapper
        // Save
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            CATEGORY_NOT_FOUND, "category not found"));
            // Jackson deserializes and copies value to the already initialised DTO
            jacksonObjectMapper.readerForUpdating(category)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));
            // save user in db using repository
            Category updatedCategory = categoryRepository.save(category);
            // Hibernate: insert into category (id, lifeevent, socialevent) values (null, ?, ?)
            // convert category details (DTO) to category (ENTITY)
            return categoryMapper.toDto(updatedCategory);
        } catch (Exception e) {
            logger.error("failed to update category", e);
        }
        return null;
    }

    public void delete(long id) {
        logger.debug("deleting category with id: {}", id);
        categoryRepository.deleteById(id);
    }

}

