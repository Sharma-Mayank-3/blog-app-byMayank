package com.blog.byMayank.controller;

import com.blog.byMayank.dto.CommentDto;
import com.blog.byMayank.service.CommentService;
import com.blog.byMayank.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog-app")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("user/{userId}/post/{postId}/comment")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CommentDto commentDto,
                                                  @PathVariable("userId") int userId,
                                                  @PathVariable("postId") int postId
                                                  ){
        CommentDto comment = this.commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(new ApiResponse("created comment", true, "comment-controller", comment), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<ApiResponse> getAllCommentsOnPost(@PathVariable("postId") int postId){
        List<CommentDto> allCommentsOnPost = this.commentService.getAllCommentsOnPost(postId);
        return new ResponseEntity<>(new ApiResponse("created comment", true, "comment-controller", allCommentsOnPost), HttpStatus.OK);
    }

    @GetMapping("/searchComments")
    public ResponseEntity<ApiResponse> searchComments(@RequestParam(value = "keyword", required = false) String keyword){
        List<CommentDto> commentDtos = this.commentService.searchComments(keyword);
        return new ResponseEntity<>(new ApiResponse("created comment", true, "comment-controller", commentDtos), HttpStatus.OK);
    }

}
