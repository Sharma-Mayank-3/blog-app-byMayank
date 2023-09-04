package com.blog.byMayank.service;

import com.blog.byMayank.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, int userId, int postId);

    List<CommentDto> getAllCommentsOnPost(int postId);

    List<CommentDto> searchComments(String keyword);

}
