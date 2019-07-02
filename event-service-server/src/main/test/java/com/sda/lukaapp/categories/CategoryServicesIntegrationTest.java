package com.sda.lukaapp.categories;

import com.sda.lukaapp.categories.rest.dto.CategoryResponse;
import com.sda.lukaapp.categories.rest.dto.CreateCategoryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class CategoryServicesIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void givenCategory_whenFindAll_thenReturnAListWithOneCategory(){
        //given
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setLifeEvent("nunta");

        //when
        categoryService.save(createCategoryRequest);

        List<CategoryResponse> expectedCategories = categoryService.findAll();

        //then
        assertThat(expectedCategories.size()).isEqualTo(1);
    }
}
