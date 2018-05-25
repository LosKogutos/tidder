package com.tidder.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name="Posts")
public class PostEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String topic;
	@NotEmpty
	private String text;
	@NotNull
	private Date date;
	
	@Transient
	private int totalLikes;
	@PostLoad
	public void onLoad() {
		this.totalLikes = likes.size();
	}
	
	@ManyToOne
	@JoinColumn(name="UserId")
	@Fetch(FetchMode.JOIN)
	private UserEntity user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<LikePostEntity> likes;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.REMOVE)
	private List<CommentEntity> comments;
	
	@Override
	public String toString() {
		StringBuffer commentsString = new StringBuffer();
		for (CommentEntity comment : comments) {
			commentsString.append("  ||  "+comment.getText());
		}
		return "Id: " + id + ", topic: " + topic + ", text: " + 
				text + ", date: " + date.toString() + ", user: " + 
				user.toString() + ", "+ commentsString.toString();
	}
	public int getTotalLikes() {
		return totalLikes;
	}
	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}
	public List<LikePostEntity> getLikes() {
		return likes;
	}
	public void setLikes(List<LikePostEntity> likes) {
		this.likes = likes;
	}
	public List<CommentEntity> getComments() {
		return comments;
	}
	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
}
