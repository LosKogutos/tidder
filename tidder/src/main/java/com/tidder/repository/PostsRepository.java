package com.tidder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tidder.model.PostEntity;

@Repository("postsRepository")
public interface PostsRepository extends JpaRepository<PostEntity, Integer> {

	@Query("SELECT p FROM PostEntity p WHERE p.id BETWEEN :from AND :to")
	public List<PostEntity> findBetweenId(@Param("from") int from, @Param("to") int to);

	@Query("SELECT count(p) FROM PostEntity p")
	public int getPostsAmmount();

}
