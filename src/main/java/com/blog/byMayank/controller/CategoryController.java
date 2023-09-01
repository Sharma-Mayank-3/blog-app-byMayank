package com.blog.byMayank.controller;

import com.blog.byMayank.dto.CategoryDto;
import com.blog.byMayank.service.CategoryService;
import com.blog.byMayank.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog-app/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new ApiResponse("category created", true, "user-service", category), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCategory(){
        List<CategoryDto> allCategory = this.categoryService.getAllCategory();
        return new ResponseEntity(new ApiResponse("all category", true, "user-service", allCategory), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable("categoryId") int categoryId){
        CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(new ApiResponse("get categorybyId", true, "user-service", categoryById), HttpStatus.OK);
    }

    @GetMapping("/matchByName")
    public ResponseEntity<ApiResponse> getCategoryByExactNameMatch(
            @RequestParam(name = "categoryTitle", required = true) String categoryTitle
    ){
        CategoryDto categoryByExactName = this.categoryService.getCategoryByExactName(categoryTitle);
        return new ResponseEntity<>(new ApiResponse("get categoryby Exact name match", true, "user-service", categoryByExactName), HttpStatus.OK);
    }

    @GetMapping("/likeNameMatch")
    public ResponseEntity<ApiResponse> getCategoryByLikeNameMatch(
            @RequestParam(name = "categoryTitle", required = true) String categoryTitle
    ){
        CategoryDto categoryByNameLike = this.categoryService.getCategoryByNameLike(categoryTitle);
        return new ResponseEntity<>(new ApiResponse("get categoryby like match", true, "user-service", categoryByNameLike), HttpStatus.OK);
    }


}
