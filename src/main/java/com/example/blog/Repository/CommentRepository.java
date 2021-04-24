package com.example.blog.Repository;

import com.example.blog.Entity.Blog;
import com.example.blog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlog(Blog blog);

}
