package com.tidder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@XmlRootElement
@Table(name="CommentLikes")
public class LikeCommentEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	@Fetch(FetchMode.JOIN)
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="CommentId")
	@Fetch(FetchMode.JOIN)
	private CommentEntity comment;

	public CommentEntity getComment() {
		return comment;
	}
	public void setComment(CommentEntity comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}

}
