package com.tidder.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.api.dto.Comment;
import com.tidder.api.dto.Post;
import com.tidder.api.dto.PostWithComments;
import com.tidder.api.dto.PostsAmount;
import com.tidder.api.dto.User;
import com.tidder.model.CommentEntity;
import com.tidder.model.PostEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.LoginRepository;
import com.tidder.repository.PostsRepository;

@Service("postsService")
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private LoginRepository loginRepository;

	@Transactional
	public void createPost(Post post) { 
		postsRepository.save(postToEntity(post));
	}
	
	@Transactional
	public List<Post> getAllPosts() { 	
		return entityToPost(postsRepository.findAll() );
	}
	
	@Transactional
	public PostsAmount getPostsAmmount() {
		PostsAmount amount = new PostsAmount();
		amount.setAmmount(postsRepository.getPostsAmmount());
		return amount;
	}
	
	@Transactional
	public PostWithComments getPostById(int id) {
		return entityToPost(postsRepository.findById(id));
	}
	
	@Transactional
	public List<Post> getPostsByPageId(int id, int amount) {
		int to = (id*amount) - 1;
		int from = (to-amount) + 1;
		return entityToPost(postsRepository.findBetweenId(from,to));
	}

	//---------HELPERS--------------------------------------------------
	
	@Transactional
	private UserEntity getAuthenticatedUser() {		
		return loginRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());		
	}
	
	//---------dao -> entity----------
	
	private PostEntity postToEntity(Post dto) {
		PostEntity entity = new PostEntity();
		try {
			entity.setTopic(dto.getTopic());
			entity.setText(dto.getText());
			entity.setDate(new Date(System.currentTimeMillis()));
			if(getAuthenticatedUser()==null) {
				entity.setUser(loginRepository.findByEmail("abcd"));
			} else {
				entity.setUser(getAuthenticatedUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	//---------entity -> dao----------
	
	private PostWithComments entityToPost(Optional<PostEntity> entityPost) {
		PostWithComments dtoPost = new PostWithComments();
		if(entityPost.isPresent()) {
			User dtoUser = new User();
			bindPostWithUser(entityPost,dtoPost,dtoUser);
			List<Comment> commentsList = new ArrayList<Comment>();
			for (CommentEntity commentEntity : entityPost.get().getComments()) {
				User user = new User();
				user.setId(commentEntity.getUser().getId());
				user.setName(commentEntity.getUser().getName());
				user.setLastname(commentEntity.getUser().getLastname());
				user.setEmail(commentEntity.getUser().getEmail());
				
				Comment comment = new Comment();
				comment.setId(commentEntity.getId());
				comment.setText(commentEntity.getText());
				comment.setDate(commentEntity.getDate());
				comment.setLikes(commentEntity.getTotalLikes());
				comment.setUser(user);
				commentsList.add(comment);
			}
			dtoPost.setComments(commentsList);

		}
		return dtoPost;
	}

	private List<Post> entityToPost(List<PostEntity> entitiesList) {
		List<Post> dtoList = new ArrayList<Post>();
		if(entitiesList != null) {
			for (PostEntity entity : entitiesList) {
				Post dtoPost = new Post();
				User dtoUser = new User();				
				bindPostWithUser(entity,dtoPost,dtoUser);
				dtoList.add(dtoPost);
			}
		}
		return dtoList;
	}
	
	//---------binders----------
	
	private void bindPostWithUser(PostEntity entityPost, Post dtoPost, User dtoUser) {
		dtoUser.setId(entityPost.getUser().getId());
		dtoUser.setName(entityPost.getUser().getName());
		dtoUser.setLastname(entityPost.getUser().getLastname());
		dtoUser.setEmail(entityPost.getUser().getEmail());
		
		dtoPost.setId(entityPost.getId());
		dtoPost.setTopic(entityPost.getTopic());
		dtoPost.setText(entityPost.getText());
		dtoPost.setDate(entityPost.getDate());
		dtoPost.setLikes(entityPost.getTotalLikes());
		dtoPost.setUser(dtoUser);
	}

	private void bindPostWithUser(Optional<PostEntity> entityPost, Post dtoPost, User dtoUser) { 
		dtoUser.setId(entityPost.get().getUser().getId());
		dtoUser.setName(entityPost.get().getUser().getName());
		dtoUser.setLastname(entityPost.get().getUser().getLastname());
		dtoUser.setEmail(entityPost.get().getUser().getEmail());
		
		dtoPost.setId(entityPost.get().getId());
		dtoPost.setTopic(entityPost.get().getTopic());
		dtoPost.setText(entityPost.get().getText());
		dtoPost.setDate(entityPost.get().getDate());
		dtoPost.setLikes(entityPost.get().getTotalLikes());
		dtoPost.setUser(dtoUser);
	}


}
