package com.example.blog.Service;

import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Controller.Resource.VisibilityRequest;
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

    public Blog saveBlog(BlogRequest blogRequest) {
        Blog newBlog = new Blog(
                blogRequest.getText(),
                blogRequest.getVisibility(),
                Objects.nonNull(blogRequest.getLocation()) ? blogRequest.getLocation().getLat() : null,
                Objects.nonNull(blogRequest.getLocation()) ? blogRequest.getLocation().getLon() : null
        );
        return blogRepository.save(newBlog);
    }

    public Slice<Blog> findAllPublicBlogs(int pageNum) {
        return blogRepository.findByVisibilityOrderByCreatedAtDesc(Visibility.PUBLIC, PageRequest.of(pageNum, PAGE_SIZE));
    }

    @Transactional
    public void updateVisibility(Long blogId, VisibilityRequest visibilityRequest) {
        Blog blog = blogRepository.getOne(blogId);
        System.out.println("Version : " + visibilityRequest.getVersion());
        blog.setVersion(visibilityRequest.getVersion());
        blog.setVisibility(visibilityRequest.getVisibility());
        /*
        blogRepository.setVisibilityFor(
                blogId,
                visibilityRequest.getVisibility()
        );
         */
    }

    public void deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
