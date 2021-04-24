package com.example.blog.Repository;

import com.example.blog.Entity.Blog;
import com.example.blog.Service.model.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findByVisibilityOrderByCreatedAtDesc(Visibility visibility, Pageable pageable);

    @Modifying
    @Query("update Blog b set b.visibility = :visibility where b.id = :blogId")
    int setVisibilityFor(
            @Param("blogId") Long blogId,
            @Param("visibility") Visibility visibility
    );
}
