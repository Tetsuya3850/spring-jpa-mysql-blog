package com.example.blog;

import com.example.blog.Controller.Resource.*;
import com.example.blog.Entity.Blog;
import com.example.blog.Service.model.Visibility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BlogApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {

		// saveBlog
		String BLOG_TEXT_1 = "BLOG_TEXT_1";
		BlogRequest blogRequest1 = new BlogRequest(
				BLOG_TEXT_1,
				Visibility.PUBLIC,
				new BlogRequest.Location(
						35.7,
						135.9
				)
		);
		HttpEntity<Object> saveBlogHttpEntity = new HttpEntity<>(blogRequest1);
		ResponseEntity<Blog> saveBlogResponse1 = restTemplate.exchange(
				"/blogs", HttpMethod.POST, saveBlogHttpEntity, Blog.class);
		assertEquals(HttpStatus.OK, saveBlogResponse1.getStatusCode());
		assertEquals(BLOG_TEXT_1, saveBlogResponse1.getBody().getText());
		Long blogId1 = saveBlogResponse1.getBody().getId();

		// saveBlog 2
		String BLOG_TEXT_2 = "BLOG_TEXT_2";
		BlogRequest blogRequest2 = new BlogRequest(
				BLOG_TEXT_2,
				Visibility.PUBLIC,
				new BlogRequest.Location(
						85.7,
						85.9
				)
		);
		HttpEntity<Object> saveBlogHttpEntity2 = new HttpEntity<>(blogRequest2);
		ResponseEntity<Blog> saveBlogResponse2 = restTemplate.exchange(
				"/blogs", HttpMethod.POST, saveBlogHttpEntity2, Blog.class);
		assertEquals(HttpStatus.OK, saveBlogResponse2.getStatusCode());
		assertEquals(BLOG_TEXT_2, saveBlogResponse2.getBody().getText());
		Long blogId2 = saveBlogResponse2.getBody().getId();

		// findAllPublicBlogs
		ResponseEntity<BlogPageResponse> findAllPublicBlogs = restTemplate.exchange(
				"/blogs", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), BlogPageResponse.class);
		assertEquals(HttpStatus.OK, findAllPublicBlogs.getStatusCode());
		assertEquals(BLOG_TEXT_2, findAllPublicBlogs.getBody().getBlogs().get(0).getText());
		assertEquals(BLOG_TEXT_1, findAllPublicBlogs.getBody().getBlogs().get(1).getText());

		// updateVisibility
		VisibilityRequest updateVisibilityRequest1 = new VisibilityRequest(
				Visibility.PRIVATE, findAllPublicBlogs.getBody().getBlogs().get(0).getVersion()
		);
		HttpEntity<Object> updateVisibilityHttpEntity1 = new HttpEntity<>(updateVisibilityRequest1);
		restTemplate.exchange(
				"/blogs/" + blogId1 + "/visibility", HttpMethod.PUT, updateVisibilityHttpEntity1, Void.class);

		// findAllPublicBlogs becomes 1
		ResponseEntity<BlogPageResponse> findAllPublicBlogsAfterPrivate = restTemplate.exchange(
				"/blogs", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), BlogPageResponse.class);
		assertEquals(HttpStatus.OK, findAllPublicBlogsAfterPrivate.getStatusCode());
		assertEquals(1, findAllPublicBlogsAfterPrivate.getBody().getBlogs().size());

		// deleteBlog
		restTemplate.exchange(
				"/blogs/" + blogId2, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Void.class);

		// findAllPublicBlogs becomes 0
		ResponseEntity<BlogPageResponse> findAllPublicBlogsAfterDelete = restTemplate.exchange(
				"/blogs", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), BlogPageResponse.class);
		assertEquals(HttpStatus.OK, findAllPublicBlogsAfterDelete.getStatusCode());
		assertEquals(0, findAllPublicBlogsAfterDelete.getBody().getBlogs().size());

		// saveComment
		String COMMENT_TEXT_1 = "COMMENT_TEXT_1";
		CommentRequest commentRequest1 = new CommentRequest(
				COMMENT_TEXT_1
		);
		HttpEntity<Object> saveCommentHttpEntity = new HttpEntity<>(commentRequest1);
		ResponseEntity<CommentResponse> saveCommentResponse1 = restTemplate.exchange(
				"/comments/blogs/" + blogId1, HttpMethod.POST, saveCommentHttpEntity, CommentResponse.class);
		assertEquals(HttpStatus.OK, saveCommentResponse1.getStatusCode());
		assertEquals(COMMENT_TEXT_1, saveCommentResponse1.getBody().getText());
		Long commentId1 = saveBlogResponse1.getBody().getId();

		// getCommentsByBlog
		ResponseEntity<List<CommentResponse>> getCommentsByBlogResponse1 = restTemplate.exchange(
				"/comments/blogs/" + blogId1, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<List<CommentResponse>>() {});
		assertEquals(HttpStatus.OK, getCommentsByBlogResponse1.getStatusCode());
		assertEquals(COMMENT_TEXT_1, getCommentsByBlogResponse1.getBody().get(0).getText());
		assertNull(getCommentsByBlogResponse1.getBody().get(0).getBlog());

		// getCommentsByText
		ResponseEntity<List<CommentResponse>> getCommentsByTextResponse = restTemplate.exchange(
				"/comments?text=" + COMMENT_TEXT_1, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<List<CommentResponse>>() {});
		assertEquals(HttpStatus.OK, getCommentsByTextResponse.getStatusCode());
		assertEquals(COMMENT_TEXT_1, getCommentsByTextResponse.getBody().get(0).getText());

		// deleteComment
		restTemplate.exchange(
				"/comments/" + commentId1, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Void.class);

		// getCommentsByBlog becomes 0
		ResponseEntity<List<CommentResponse>> getCommentsByBlogResponse2 = restTemplate.exchange(
				"/comments/blogs/" + blogId1, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<List<CommentResponse>>() {});
		assertEquals(HttpStatus.OK, getCommentsByBlogResponse2.getStatusCode());
		assertEquals(0, getCommentsByBlogResponse2.getBody().size());
	}

}
