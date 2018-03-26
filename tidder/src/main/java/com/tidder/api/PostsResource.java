package com.tidder.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tidder.api.dto.Post;
import com.tidder.api.dto.PostWithComments;
import com.tidder.service.PostsService;

@Component
@Path("post")
public class PostsResource {
	
	@Autowired
	private PostsService postsService;
	
	/**
	 * http://localhost:8080/tidder/webapi/post/all
	 * 
	 * Produces JSON of all posts with their authors
	 * 
	 * @return - list of posts
	 */
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getAllPosts() {
		return postsService.getAllPosts();
	}
	
	/**
	 * http://localhost:8080/tidder/webapi/post/1
	 * 
	 * Produces JSON of specified by id post 
	 * with his author and all comments with authors
	 * 
	 * @param id - id of a post  
	 * @return - single post with all comments associated 
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PostWithComments getPostById(@PathParam("id") String id) {
		return postsService.getPostById(Integer.parseInt(id));
	}
}
