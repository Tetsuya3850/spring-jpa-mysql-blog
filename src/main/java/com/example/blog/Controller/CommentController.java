package com.example.blog.Controller;

import com.example.blog.Controller.Resource.CommentRequest;
import com.example.blog.Controller.Resource.CommentResponse;
import com.example.blog.Entity.Comment;
import com.example.blog.Entity.CommentTextOnly;
import com.example.blog.Service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/blogs/{blogId}")
    CommentResponse saveComment(
            @PathVariable Long blogId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        Comment comment = commentService.saveComment(blogId, commentRequest);
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                null
        );
    }

    @GetMapping("/blogs/{blogId}")
    List<CommentResponse> getCommentsOfBlog(
            @PathVariable Long blogId
    ) {
        List<Comment> comments = commentService.getCommentsOfBlog(blogId);
        return comments.stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getText(),
                        c.getCreatedAt(),
                        null
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("")
    List<CommentTextOnly> getCommentsByText(
            @RequestParam String text
    ) {
        return commentService.getCommentsByText(text);
    }

    @DeleteMapping("/{commentId}")
    void deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
    }
}
