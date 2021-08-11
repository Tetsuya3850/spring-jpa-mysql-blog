package com.example.blog.Controller;

import com.example.blog.Controller.Resource.BlogPageResponse;
import com.example.blog.Controller.Resource.NewBlogRequest;
import com.example.blog.Controller.Resource.UpdateBlogRequest;
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
            @Valid @RequestBody NewBlogRequest newBlogRequest
    ) {
        return blogService.saveBlog(newBlogRequest);
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

    @PutMapping("/{blogId}")
    void updateBlog(
            @PathVariable Long blogId,
            @Valid @RequestBody UpdateBlogRequest updateBlogRequest
    ) {
        blogService.updateBlog(blogId, updateBlogRequest);
    }

    @DeleteMapping("/{blogId}")
    void deleteBlog(
            @PathVariable Long blogId
    ) {
        blogService.deleteBlog(blogId);
    }
}
