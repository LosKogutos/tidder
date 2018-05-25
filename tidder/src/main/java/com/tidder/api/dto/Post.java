package com.tidder.api.dto;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable {

	private static final long serialVersionUID = 4960004591438540101L;
	private int id;
	private String topic;
	private String text;
	private Date date;
	private User user;
	private int likes;

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


}
