package com.blog.byMayank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int postId;
    private String postTitle;
    private String postContent;

    private UserDto user;

    private CategoryDto category;
}
