package com.example.blog.Controller.Resource;

import com.example.blog.Entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long id;
    private String text;
    private Date createdAt;
    private Blog blog;
}
