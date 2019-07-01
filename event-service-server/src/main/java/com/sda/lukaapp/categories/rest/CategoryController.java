package com.sda.lukaapp.categories.rest;

import com.sda.lukaapp.categories.CategoryService;
import com.sda.lukaapp.categories.rest.dto.CategoryResponse;
import com.sda.lukaapp.categories.rest.dto.CreateCategoryRequest;
import com.sda.lukaapp.categories.rest.dto.UpdateCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(CategoryController.API_CATEGORIES)
public class CategoryController {

    public static final String API_CATEGORIES = "/categories";

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable("id") final long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryResponse create(@RequestBody final CreateCategoryRequest createAccountRequest) {
        return categoryService.save(createAccountRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@RequestBody final UpdateCategoryRequest updateCategoryRequest,
                                   @PathVariable("id") final long id) {
        return categoryService.update(updateCategoryRequest, id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryResponse patch(@RequestBody final Map<String, Object> updates,
                                  @PathVariable("id") final long id) {
        return categoryService.partialUpdate(updates, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long id) {
        categoryService.delete(id);
    }
}


