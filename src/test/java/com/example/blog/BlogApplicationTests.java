package com.example.blog;

import com.example.blog.Controller.Resource.BlogPageResponse;
import com.example.blog.Controller.Resource.BlogRequest;
import com.example.blog.Entity.Blog;
import com.example.blog.Service.model.Visibility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BlogApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void test() {

		// saveBlog
		String BLOG_TEXT_1 = "BLOG_TEXT_1";
		BlogRequest blogRequest1 = new BlogRequest(BLOG_TEXT_1, Visibility.PUBLIC, null);
		HttpEntity<Object> saveBlogHttpEntity = new HttpEntity<>(blogRequest1);
		ResponseEntity<Blog> saveBlogResponse = restTemplate.exchange(
				"/blogs", HttpMethod.POST, saveBlogHttpEntity, Blog.class);
		assertEquals(HttpStatus.OK, saveBlogResponse.getStatusCode());
		assertEquals(BLOG_TEXT_1, saveBlogResponse.getBody().getText());
		Long blogId1 = saveBlogResponse.getBody().getId();

		// findAllPublicBlogs
		ResponseEntity<BlogPageResponse> findAllPublicBlogs = restTemplate.exchange(
				"/blogs", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<BlogPageResponse>() {});
		assertEquals(HttpStatus.OK, saveBlogResponse.getStatusCode());
		assertEquals(BLOG_TEXT_1, findAllPublicBlogs.getBody().getBlogs().get(0).getText());

		/*
		// Add Seed Data
		String ARTICLE_TITLE_2 = ARTICLE_TITLE + "_2";
		restTemplate.exchange("/articles", HttpMethod.POST, new HttpEntity<>(new FormArticle(ARTICLE_TITLE_2, ARTICLE_TEXT), headers), Article.class);
		TestUtils.threadSleep(1000);
		String USERNAME_2 = USERNAME + "_2";
		FormPerson formPerson2 = new FormPerson(USERNAME_2, PASSWORD);
		restTemplate.postForEntity("/persons/signup", formPerson2, Person.class);
		ResponseEntity<String> loginResponse2 = restTemplate.postForEntity("/login", formPerson2, String.class);
		String token2 = loginResponse2.getBody();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setContentType(MediaType.APPLICATION_JSON);
		headers2.set(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token2);
		String ARTICLE_TITLE_3 = ARTICLE_TITLE + "_3";
		restTemplate.exchange("/articles", HttpMethod.POST, new HttpEntity<>(
				new FormArticle(ARTICLE_TITLE_3, ARTICLE_TEXT), headers2), Article.class);
		TestUtils.threadSleep(1000);
		String ARTICLE_TITLE_4 = ARTICLE_TITLE + "_4";
		restTemplate.exchange("/articles", HttpMethod.POST, new HttpEntity<>(
				new FormArticle(ARTICLE_TITLE_4, ARTICLE_TEXT), headers2), Article.class);

		// Feed first page
		ResponseEntity<RestPageImpl<Article>> getFeedResponse = restTemplate.exchange(
				"/articles", HttpMethod.GET, baseHttpEntity, new ParameterizedTypeReference<RestPageImpl<Article>>() {});
		assertEquals(HttpStatus.OK, getFeedResponse.getStatusCode());
		assertEquals(ARTICLE_TITLE_4, getFeedResponse.getBody().getContent().get(0).getTitle());
		assertEquals(USERNAME_2, getFeedResponse.getBody().getContent().get(0).getPerson().getUsername());

		// Feed next page
		ResponseEntity<RestPageImpl<Article>> getNextPageFeedResponse = restTemplate.exchange(
				"/articles?page={page}", HttpMethod.GET, baseHttpEntity, new ParameterizedTypeReference<RestPageImpl<Article>>() {}, 1);
		assertEquals(HttpStatus.OK, getNextPageFeedResponse.getStatusCode());
		assertEquals(ARTICLE_TITLE_1, getNextPageFeedResponse.getBody().getContent().get(0).getTitle());
		assertEquals(USERNAME, getNextPageFeedResponse.getBody().getContent().get(0).getPerson().getUsername());

		// User articles
		ResponseEntity<List<Article>> getPersonArticlesResponse = restTemplate.exchange(
				"/articles/persons/{personId}", HttpMethod.GET, baseHttpEntity, new ParameterizedTypeReference<List<Article>>() {}, person1Id);
		assertEquals(HttpStatus.OK, getPersonArticlesResponse.getStatusCode());
		assertEquals(2, getPersonArticlesResponse.getBody().size());
		assertEquals(ARTICLE_TITLE_2, getPersonArticlesResponse.getBody().get(0).getTitle());
		assertEquals(USERNAME, getPersonArticlesResponse.getBody().get(0).getPerson().getUsername());

		// Get article
		ResponseEntity<Article> getArticleResponse = restTemplate.exchange(
				"/articles/{articleId}", HttpMethod.GET, baseHttpEntity, Article.class, article1Id);
		assertEquals(HttpStatus.OK, getArticleResponse.getStatusCode());
		assertEquals(ARTICLE_TITLE_1, getArticleResponse.getBody().getTitle());
		assertEquals(USERNAME, getArticleResponse.getBody().getPerson().getUsername());
		assertNull(getArticleResponse.getBody().getPerson().getPassword());

		// Edit article
		String EDIT_TITLE = "EDIT_TITLE";
		FormArticle editArticle = new FormArticle(EDIT_TITLE, ARTICLE_TEXT);
		HttpEntity<Object> editArticleHttpEntity = new HttpEntity<>(editArticle, headers);
		ResponseEntity<Article> editArticleResponse = restTemplate.exchange(
				"/articles/{articleId}", HttpMethod.PUT, editArticleHttpEntity, Article.class, article1Id);
		assertEquals(HttpStatus.OK, editArticleResponse.getStatusCode());
		assertEquals(EDIT_TITLE, editArticleResponse.getBody().getTitle());

		// Delete article
		ResponseEntity<Void> deleteArticleResponse = restTemplate.exchange(
				"/articles/{articleId}", HttpMethod.DELETE, baseHttpEntity, Void.class, article1Id);
		assertEquals(HttpStatus.OK, deleteArticleResponse.getStatusCode());
		ResponseEntity<List<Article>> getPersonArticlesAgainResponse = restTemplate.exchange(
				"/articles/persons/{personId}", HttpMethod.GET, baseHttpEntity, new ParameterizedTypeReference<List<Article>>() {}, person1Id);
		assertEquals(HttpStatus.OK, getPersonArticlesAgainResponse.getStatusCode());
		assertEquals(1, getPersonArticlesAgainResponse.getBody().size());

		 */
	}

}
