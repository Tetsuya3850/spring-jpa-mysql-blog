package com.example.blog.Service;

import com.example.blog.Controller.Resource.CommentRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Entity.Comment;
import com.example.blog.Repository.BlogRepository;
import com.example.blog.Repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment saveComment(
            Long blogId,
            CommentRequest commentRequest
    ) {
        Blog blog = blogRepository
                .findById(blogId)
                .orElseThrow(() -> new RuntimeException());
        Comment newComment = new Comment(
                commentRequest.getText(),
                System.currentTimeMillis() / 1000,
                blog
        );
        return commentRepository.save(newComment);
    }
}
