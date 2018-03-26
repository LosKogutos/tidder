package com.tidder.api.dto;

import java.io.Serializable;
import java.util.List;


public class PostWithComments extends Post implements Serializable {

	private static final long serialVersionUID = 8914888778533651149L;
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
