package com.tidder.service;

import com.tidder.api.dto.LikeResponse;

public interface LikesService {

	LikeResponse likePost(int postId);

	LikeResponse likeComment(int postId);
	
}
