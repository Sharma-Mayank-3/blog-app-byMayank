package com.blog.byMayank.service;

import com.blog.byMayank.dto.CategoryDto;
import com.blog.byMayank.repository.CategoryRepo;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategoryById(int categoryId);

    CategoryDto getCategoryByNameLike(String categoryTitle);

    CategoryDto getCategoryByExactName(String categoryTitle);

}
