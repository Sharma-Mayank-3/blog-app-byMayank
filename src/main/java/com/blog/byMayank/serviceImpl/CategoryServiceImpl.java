package com.blog.byMayank.serviceImpl;

import com.blog.byMayank.dto.CategoryDto;
import com.blog.byMayank.entity.Category;
import com.blog.byMayank.exception.ResourceNotFoundException;
import com.blog.byMayank.repository.CategoryRepo;
import com.blog.byMayank.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category map = this.modelMapper.map(categoryDto, Category.class);
        Category save = this.categoryRepo.save(map);
        return this.modelMapper.map(save, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> all = this.categoryRepo.findAll();
        List<CategoryDto> collect = all.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CategoryDto getCategoryById(int categoryId) {
        try {
            Category byCategoryId = this.categoryRepo.findByCategoryId(categoryId);
            return this.modelMapper.map(byCategoryId, CategoryDto.class);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Catgeory", "categoryId", categoryId);
        }

    }

    @Override
    public CategoryDto getCategoryByNameLike(String categoryTitle) {
        Category categoryByTitleLike = this.categoryRepo.getCategoryByTitleLike(categoryTitle);
        return this.modelMapper.map(categoryByTitleLike, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryByExactName(String categoryTitle) {
        Category categoryByTitleLike = this.categoryRepo.getCategoryByTitleExactMatch(categoryTitle);
        return this.modelMapper.map(categoryByTitleLike, CategoryDto.class);
    }
}
