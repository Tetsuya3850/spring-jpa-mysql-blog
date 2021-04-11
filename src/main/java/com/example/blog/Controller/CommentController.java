package com.example.blog.Controller;

import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Controller.Resource.CommentRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Entity.Comment;
import com.example.blog.Service.BlogService;
import com.example.blog.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/blogs/{blogId}")
    Comment saveComment(
            @PathVariable Long blogId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        return commentService.saveComment(blogId, commentRequest);
    }
}
