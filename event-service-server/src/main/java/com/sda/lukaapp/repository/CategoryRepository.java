package com.sda.lukaapp.repository;

import com.sda.lukaapp.categories.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

