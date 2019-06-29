package com.sda.lukaapp.categories.rest.dto;

import com.sda.lukaapp.categories.domain.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    // dto to entity conversions

    public Category toEntity(CreateCategoryRequest dto) {
        Category category = new Category();
        category.setLifeEvent(dto.getLifeEvent());
        category.setSocialEvent(dto.getSocialEvent());
        return category;
    }

    // entity to dto conversions

    public List<CategoryResponse> toDto(List<Category> categories) {
        return categories.stream()
                .map(category -> this.toDto(category))
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> toDtoOldWay(List<Category> givenList) {
        // create new list for modified objects
        List<CategoryResponse> resultList = new ArrayList<>();

        // for each item in given list
        for (Category category : givenList) {

            // transform to dto (by copying all fields)

            CategoryResponse categoryResponse = this.toDto(category);

            // add transformed item to the new list
            resultList.add(categoryResponse);
        }
        return resultList;
    }

    public CategoryResponse toDto(Category category) {
        CategoryResponse categoryResonse = new CategoryResponse();
        categoryResonse.setLifeEvent(category.getLifeEvent());
        categoryResonse.setSocialEvent(category.getSocialEvent());
        return categoryResonse;
    }

    public void map(UpdateCategoryRequest request, Category category) {
        if (request.getLifeEvent() != null) {
            category.setLifeEvent(request.getLifeEvent());
        }
        if (request.getSocialEvent() != null) {
            category.setSocialEvent(request.getSocialEvent());
        }

    }
}
