package com.blog.byMayank.serviceImpl;

import com.blog.byMayank.dto.PostDto;
import com.blog.byMayank.entity.Category;
import com.blog.byMayank.entity.Post;
import com.blog.byMayank.entity.User;
import com.blog.byMayank.exception.ResourceNotFoundException;
import com.blog.byMayank.repository.CategoryRepo;
import com.blog.byMayank.repository.PostRepo;
import com.blog.byMayank.repository.UserRepo;
import com.blog.byMayank.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post map = this.modelMapper.map(postDto, Post.class);
        map.setUser(user);
        map.setCategory(category);

        Post save = this.postRepo.save(map);
        return this.modelMapper.map(save, PostDto.class);
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> all = this.postRepo.findAll();
        return all.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public String deletePost(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);
        return "post deleted";
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Post map = this.modelMapper.map(postDto, Post.class);
        map.setPostTitle(postDto.getPostTitle());
        map.setPostId(post.getPostId());
        map.setPostContent(postDto.getPostContent());
        map.setUser(post.getUser());
        map.setCategory(post.getCategory());

        Post save = this.postRepo.save(map);
        return this.modelMapper.map(save, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> allPostByUser = this.postRepo.getAllPostByUser(user);
        return allPostByUser.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByCategory(int categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> byCategory = this.postRepo.findByCategory(category);
        return byCategory.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchByKeyword(String keyword) {
        List<Post> posts = this.postRepo.searchByKeyword(keyword);
        return posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
