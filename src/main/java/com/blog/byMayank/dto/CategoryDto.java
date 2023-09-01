package com.blog.byMayank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private int categoryId;

    private String categoryTitle;
    private String categoryDescription;
}
