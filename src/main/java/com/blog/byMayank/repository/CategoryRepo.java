package com.blog.byMayank.repository;

import com.blog.byMayank.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.categoryTitle like %:categoryTitle%")
    Category getCategoryByTitleLike(@Param("categoryTitle") String CategoryTitle);

    @Query(value = "select c from Category c where c.categoryTitle= :categoryTitle")
    Category getCategoryByTitleExactMatch(@Param("categoryTitle") String CategoryTitle);

    Category findByCategoryId(int categoryId);

}
