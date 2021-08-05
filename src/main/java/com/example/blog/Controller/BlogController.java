package com.example.blog.Controller;

import com.example.blog.Controller.Resource.BlogPageResponse;
import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Controller.Resource.VisibilityRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Slice;
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
    BlogPageResponse findAllPublicBlogs(
            @RequestParam(defaultValue = "0") int pageNum
    ) {
        Slice<Blog> blogPage = blogService.findAllPublicBlogs(pageNum);
        return new BlogPageResponse(
                blogPage.hasNext(),
                blogPage.getContent()
        );
    }

    @PutMapping("/{blogId}/visibility")
    void updateVisibility(
            @PathVariable Long blogId,
            @Valid @RequestBody VisibilityRequest visibilityRequest
    ) {
        blogService.updateVisibility(blogId, visibilityRequest);
    }

    @DeleteMapping("/{blogId}")
    void deleteBlog(
            @PathVariable Long blogId
    ) {
        blogService.deleteBlog(blogId);
    }
}
