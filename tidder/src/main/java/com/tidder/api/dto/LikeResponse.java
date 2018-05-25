package com.tidder.api.dto;

import java.io.Serializable;

public class LikeResponse implements Serializable {

	private static final long serialVersionUID = 4392707934392106081L;
	private boolean isLiked;
	
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
}
