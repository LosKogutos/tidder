package com.tidder.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tidder.api.dto.Comment;
import com.tidder.model.CommentEntity;
import com.tidder.model.UserEntity;
import com.tidder.repository.CommentsRepository;
import com.tidder.repository.LoginRepository;
import com.tidder.repository.PostsRepository;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;

	@Transactional
	public void createComment(Comment comment, int postId) {
		commentsRepository.save(commentToEntity(comment, postId));
	}
	
	//---------HELPERS--------------------------------------------------
	
	@Transactional
	private UserEntity getAuthenticatedUser() {		
		return loginRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());		
	}
	
	//---------dao -> entity----------

	private CommentEntity commentToEntity(Comment comment, int postId) {
		CommentEntity entity = new CommentEntity();
		try {
			entity.setText(comment.getText());
			entity.setDate(new Date(System.currentTimeMillis()));
			entity.setPost(postsRepository.findById(postId).get());
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

}
