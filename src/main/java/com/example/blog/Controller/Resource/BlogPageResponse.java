package com.example.blog.Controller.Resource;

import com.example.blog.Entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BlogPageResponse {
    private long totalCount;
    private List<Blog> blogs;
}
