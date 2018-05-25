package com.tidder.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tidder.api.dto.Comment;
import com.tidder.api.dto.LikeResponse;
import com.tidder.api.dto.Post;
import com.tidder.api.dto.PostWithComments;
import com.tidder.api.dto.PostsAmount;
import com.tidder.service.CommentsService;
import com.tidder.service.LikesService;
import com.tidder.service.PostsService;

@Component
@Path("post")
public class PostsResource {
	
	@Autowired
	private PostsService postsService;
	
	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private LikesService likesService;
	
	/**
	 * http://localhost:8080/tidder/webapi/post/like/post?id=155
	 * 
	 * Triggers the like button on specified post.
	 * Query parameter points to ID of the post.
	 * 
	 * Doesn't consume and doesn't produce anything
	 */
	@POST
	@Path("like/post")
	public LikeResponse likePost(@QueryParam("id") int id) {
		return likesService.likePost(id);
	}
	
	/**
	 *  http://localhost:8080/tidder/webapi/post/like/comment?id=212
	 *  
	 *  Triggers the like button on specified comment.
	 *  Query parameter points to ID of the comment.
	 *  
	 *  Doesn't consume and doesn't produce anything
	 */
	@POST
	@Path("like/comment")
	public LikeResponse likeComment(@QueryParam("id") int id) {
		return likesService.likeComment(id);
	}
	
	/**
	 * http://localhost:8080/tidder/webapi/post/{id}/comment
	 * 
	 * Commits to database a new comment for post specified in URL by {id} 
	 * in format like below:
	 * {
	 * 		"text":"example"
	 * }
	 * and returns JSON with that post with all its comments
	 * 
	 * @param id - id of the commented post
	 * @param comment - submitted comment
	 * @return commented post with all data
	 */
	@POST
	@Path("{id}/comment")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public PostWithComments createComment(@PathParam("id") String id, Comment comment) {
		int postId = Integer.parseInt(id);
		commentsService.createComment(comment,postId);
		return postsService.getPostById(postId);
	}
	
	/**
	 * http://localhost:8080/tidder/webapi/post/new
	 * 
	 * Commits to database a post submitted in JSON format like below:
	 * 	{
	 * 		"topic":"example",
	 * 		"text":"example"
	 * 	}
	 * and returns JSON with full information of it.
	 * 
	 * @param post - submitted post
	 * @return - submitted post with all data
	 */
	@POST
	@Path("new")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Post createPost(Post post) {
		postsService.createPost(post);
		return post;
	}
	
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
	
	/**
	 * http://localhost:8080/tidder/webapi/post/page/2?size=5
	 * 
	 * Produces JSON of specified by page id
	 * posts with their authors. Amount of posts 
	 * is specified by query parameter.
	 * With no query parameter, default size is 10 
	 * 
	 * @param id - id of a page  
	 * @param size - size of a single page
	 * @return - list of posts
	 */
	@GET
	@Path("page/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPostsByPageId(@PathParam("id") String id,
			@DefaultValue("10") @QueryParam("size") int size)  {
		return postsService.getPostsByPageId(Integer.parseInt(id), size);
	}
	
	/**
	 * http://localhost:8080/tidder/webapi/post/all/amount
	 * 
	 * Produces JSON which shows the amount of existing posts
	 * 
	 * @return - amount of posts
	 */
	@GET
	@Path("all/amount")
	@Produces(MediaType.APPLICATION_JSON)
	public PostsAmount getPostsAmmount() {
		return postsService.getPostsAmmount();
	}
}

