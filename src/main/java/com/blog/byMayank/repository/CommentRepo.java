package com.blog.byMayank.repository;

import com.blog.byMayank.entity.Comment;
import com.blog.byMayank.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    @Query(value = "select c from Comment c where c.post= :post")
    List<Comment> getCommentsByPost(@Param("post") Post post);

    // In this search based on containing(like)
    List<Comment> findByCommentContentContaining(String keyword);


    // In this search based on exact keyword
    List<Comment> findByCommentContentLike(String keyword);

}
