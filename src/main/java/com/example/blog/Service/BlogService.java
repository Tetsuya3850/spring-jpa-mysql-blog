package com.example.blog.Service;

import com.example.blog.Controller.Resource.NewBlogRequest;
import com.example.blog.Controller.Resource.UpdateBlogRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Repository.BlogRepository;
import com.example.blog.Service.model.Visibility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@AllArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public final int PAGE_SIZE = 3;

    public Blog saveBlog(NewBlogRequest newBlogRequest) {
        Blog newBlog = new Blog(
                newBlogRequest.getText(),
                newBlogRequest.getVisibility(),
                Objects.nonNull(newBlogRequest.getLocation()) ? newBlogRequest.getLocation().getLat() : null,
                Objects.nonNull(newBlogRequest.getLocation()) ? newBlogRequest.getLocation().getLon() : null
        );
        return blogRepository.save(newBlog);
    }

    public Slice<Blog> findAllPublicBlogs(int pageNum) {
        return blogRepository.findByVisibilityOrderByCreatedAtDesc(Visibility.PUBLIC, PageRequest.of(pageNum, PAGE_SIZE));
    }

    public void updateBlog(Long blogId, UpdateBlogRequest updateBlogRequest) {
        Blog updateBlog = new Blog(
                blogId,
                updateBlogRequest.getText(),
                updateBlogRequest.getVisibility(),
                Objects.nonNull(updateBlogRequest.getLocation()) ? updateBlogRequest.getLocation().getLat() : null,
                Objects.nonNull(updateBlogRequest.getLocation()) ? updateBlogRequest.getLocation().getLon() : null,
                updateBlogRequest.getVersion()
        );
       blogRepository.save(updateBlog);
    }

    public void deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
