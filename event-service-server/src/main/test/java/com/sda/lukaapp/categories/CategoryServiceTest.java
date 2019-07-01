package com.sda.lukaapp.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.categories.domain.Category;
import com.sda.lukaapp.categories.domain.CategoryRepository;
import com.sda.lukaapp.categories.rest.dto.CategoryMapper;
import com.sda.lukaapp.categories.rest.dto.CategoryResponse;
import com.sda.lukaapp.categories.rest.dto.CreateCategoryRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {


    @RunWith(MockitoJUnitRunner.class)
    public class UserServiceTest {

        @Mock
        private CategoryRepository categoryRepository;
        @Mock
        private CategoryMapper categoryMapper;
        @Mock
        private ObjectMapper jacksonObjectMapper;

        @InjectMocks
        private CategoryService categoryService;

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        public UserServiceTest() {
        }

        @Test
        public void findAll() {
        }

        @Test
        public void givenUser_whenFindById_thenReturnUser() {
            CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
            createCategoryRequest.setLifeEvent("testlifeevent");
            createCategoryRequest.setSocialEvent("testsocialevent");

            when(categoryRepository.save(any(Category.class))).thenReturn(new Category());

            CategoryResponse created = categoryService.save(createCategoryRequest);

            assertThat(created.getLifeEvent()).isSameAs(createCategoryRequest.getSocialEvent());
        }

        @Test(expected = NullPointerException.class)
        public void save() {
        }

        @Test
        public void update() {
        }

        @Test
        public void partialUpdate() {
        }

        @Test
        public void delete() {
        }

        @Test
        public void findByEmail() {
        }
    }

}
