package com.blog.byMayank.serviceImpl;

import com.blog.byMayank.dto.CommentDto;
import com.blog.byMayank.entity.Comment;
import com.blog.byMayank.entity.Post;
import com.blog.byMayank.entity.User;
import com.blog.byMayank.exception.ResourceNotFoundException;
import com.blog.byMayank.repository.CommentRepo;
import com.blog.byMayank.repository.PostRepo;
import com.blog.byMayank.repository.UserRepo;
import com.blog.byMayank.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int userId, int postId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment map = this.modelMapper.map(commentDto, Comment.class);
        map.setUser(user);
        map.setPost(post);
        Comment save = this.commentRepo.save(map);

        return this.modelMapper.map(save, CommentDto.class);

    }

    @Override
    public List<CommentDto> getAllCommentsOnPost(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        List<Comment> commentsByPost = this.commentRepo.getCommentsByPost(post);
        return commentsByPost.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> searchComments(String keyword) {
        List<Comment> byCommentContentLike = this.commentRepo.findByCommentContentContaining(keyword);
        return byCommentContentLike.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }
}
