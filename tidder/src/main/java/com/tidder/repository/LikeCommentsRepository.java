package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tidder.model.LikeCommentEntity;

@Repository("likeCommentsRepository")
public interface LikeCommentsRepository extends JpaRepository<LikeCommentEntity, Integer> {

	//DELETE FROM tidder.CommentLikes WHERE UserId= ? AND CommentId= ?
	@Modifying
	@Query("DELETE FROM LikeCommentEntity e WHERE e.user.id = :user AND e.comment.id = :comment")
	public void delete(@Param("user")int user, @Param("comment") int comment);

	//SELECT * FROM tidder.CommentLikes WHERE UserId= ? AND CommentId= ?
	@Query("SELECT e FROM LikeCommentEntity e WHERE e.user.id = :user AND e.comment.id = :comment")
	public LikeCommentEntity getByIds(@Param("user")int user, @Param("comment")int comment);
		
}
