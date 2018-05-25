package com.tidder.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@XmlRootElement
@Table(name="Users")
@NamedEntityGraph(name="authorization",
	attributeNodes = @NamedAttributeNode(value="authorities"))
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -8782855747936633473L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String email;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastname;
	@NotEmpty
	private String password;
	private boolean enabled;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<LikeCommentEntity> commentLikes;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<LikePostEntity> postLikes;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private Set<AuthoritiesEntity> authorities;

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<PostEntity> posts;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<CommentEntity> comments;
	
	public List<LikeCommentEntity> getCommentLikes() {
		return commentLikes;
	}
	public void setCommentLikes(List<LikeCommentEntity> commentLikes) {
		this.commentLikes = commentLikes;
	}
	public List<LikePostEntity> getPostLikes() {
		return postLikes;
	}
	public void setPostLikes(List<LikePostEntity> postLikes) {
		this.postLikes = postLikes;
	}
	public Set<AuthoritiesEntity> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<AuthoritiesEntity> authorities) {
		this.authorities = authorities;
	}
	public List<PostEntity> getPosts() {
		return posts;
	}
	public void setPosts(List<PostEntity> posts) {
		this.posts = posts;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
