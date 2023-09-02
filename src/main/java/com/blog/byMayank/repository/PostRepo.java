package com.blog.byMayank.repository;

import com.blog.byMayank.entity.Category;
import com.blog.byMayank.entity.Post;
import com.blog.byMayank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category category);

    @Query(value = "select p from Post p where p.user= :user")
    List<Post> getAllPostByUser(@Param("user") User user);

    @Query(value = "select p from Post p where p.postTitle like %:keyword%")
    List<Post> searchByKeyword(@Param("keyword") String keyword);

}
