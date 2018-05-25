package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tidder.model.LikePostEntity;

@Repository("likePostsRepository")
public interface LikePostsRepository extends JpaRepository<LikePostEntity, Integer> {
	
	//DELETE FROM tidder.PostLikes WHERE UserId= ? AND PostId= ?
	@Modifying
	@Query("DELETE FROM LikePostEntity e WHERE e.user.id = :user AND e.post.id = :post")
	public void delete(@Param("user")int user, @Param("post") int post);

	//SELECT * FROM tidder.PostLikes WHERE UserId= ? AND PostId= ?
	@Query("SELECT e FROM LikePostEntity e WHERE e.user.id = :user AND e.post.id = :post")
	public LikePostEntity getByIds(@Param("user")int user, @Param("post")int post);
		
	
}
