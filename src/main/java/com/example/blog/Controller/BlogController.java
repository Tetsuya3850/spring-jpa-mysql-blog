package com.example.blog.Controller;

import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Controller.Resource.VisibilityRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("")
    Blog saveBlog(
            @Valid @RequestBody BlogRequest blogRequest
    ) {
        return blogService.saveBlog(blogRequest);
    }

    @GetMapping("")
    Page<Blog> findAllPublicBlogs(
            @RequestParam(defaultValue="0") int pageNum
    ) {
        return blogService.findAllPublicBlogs(pageNum);
    }

    @PostMapping("/{blogId}/visibility")
    void updateVisibility(
            @PathVariable Long blogId,
            @Valid @RequestBody VisibilityRequest visibilityRequest
    ) {
        blogService.updateVisibility(blogId, visibilityRequest);
    }
}
