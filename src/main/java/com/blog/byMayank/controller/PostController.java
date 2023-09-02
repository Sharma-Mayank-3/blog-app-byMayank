package com.blog.byMayank.controller;

import com.blog.byMayank.dto.PostDto;
import com.blog.byMayank.service.PostService;
import com.blog.byMayank.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog-app")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<ApiResponse> createPost(
            @RequestBody PostDto postDto,
            @PathVariable("userId") int userId,
            @PathVariable("categoryId") int categoryId
    ){
        PostDto post = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(new ApiResponse("new post", true, "post-service", post), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable("postId") int postId){
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<>(new ApiResponse("postByID", true, "post-service", postById), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPost(){
        List<PostDto> allPosts = this.postService.getAllPosts();
        return new ResponseEntity<>(new ApiResponse("All Posts", true, "post-service", allPosts), HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") int postId){
        String s = this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Delete", true, "post-service", s), HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto postDto,
                                                  @PathVariable("postId") int postId){
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(new ApiResponse("update", true, "post-service", postDto1), HttpStatus.OK);
    }


    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<ApiResponse> getAllPostByUser(@PathVariable("userId") int userId){
        List<PostDto> allPostByUser = this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(new ApiResponse("all post by user", true, "post-service", allPostByUser), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse> getAllPostByCategory(@PathVariable("categoryId") int categoryId){
        List<PostDto> allPostByUser = this.postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("all post by category", true, "post-service", allPostByUser), HttpStatus.OK);
    }

    @GetMapping("/search/posts")
    public ResponseEntity<ApiResponse> searchPostInTitle(
            @RequestParam(value = "keyword", required = false) String keyword
    ){
        List<PostDto> postDtos = this.postService.searchByKeyword(keyword);
        return new ResponseEntity<>(new ApiResponse("all post by keyword", true, "post-service", postDtos), HttpStatus.OK);
    }

}
