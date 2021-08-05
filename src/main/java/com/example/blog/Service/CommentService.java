package com.example.blog.Service;

import com.example.blog.Controller.Resource.CommentRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Entity.Comment;
import com.example.blog.Entity.CommentTextOnly;
import com.example.blog.Repository.BlogRepository;
import com.example.blog.Repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public Comment saveComment(
            Long blogId,
            CommentRequest commentRequest
    ) {
        Blog blog = blogRepository.getOne(blogId);
        Comment newComment = new Comment(
                commentRequest.getText(),
                blog
        );
        return commentRepository.save(newComment);
    }

    public List<Comment> getCommentsOfBlog(
            Long blogId
    ) {
        Blog blog = blogRepository.getOne(blogId);
        return commentRepository.findByBlog(blog);
    }

    public List<CommentTextOnly> getCommentsByText(
            String text
    ) {
        return commentRepository.findByText(text);
    }

    public void deleteComment(
            Long commentId
    ) {
        commentRepository.deleteById(commentId);
    }
}
