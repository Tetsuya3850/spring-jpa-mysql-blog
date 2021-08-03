package com.example.blog.Repository;

import com.example.blog.Entity.Blog;
import com.example.blog.Entity.Comment;
import com.example.blog.Entity.CommentTextOnly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlog(Blog blog);

    List<CommentTextOnly> findByText(String text);

}
