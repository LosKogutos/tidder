package com.tidder.service;

import com.tidder.api.dto.Comment;

public interface CommentsService {

	public void createComment(Comment comment, int postId);
}
