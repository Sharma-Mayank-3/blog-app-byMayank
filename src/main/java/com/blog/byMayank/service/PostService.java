package com.blog.byMayank.service;

import com.blog.byMayank.dto.PostDto;
import com.blog.byMayank.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, int userId, int categoryId);

    PostDto getPostById(int postId);

    List<PostDto> getAllPosts();

    String deletePost(int postId);

    PostDto updatePost(PostDto postDto, int postId);

    List<PostDto> getAllPostByUser(int userId);

    List<PostDto> getAllPostByCategory(int categoryId);

    List<PostDto> searchByKeyword(String keyword);

    PostResponse getAllPostsByPageNumberandSize(int pageNumber, int pageSize, String sortBy, String dir);

}
