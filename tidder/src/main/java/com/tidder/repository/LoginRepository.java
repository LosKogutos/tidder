package com.tidder.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tidder.model.UserEntity;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<UserEntity, Integer> {

	@Query("SELECT u FROM UserEntity u WHERE u.email = :email")
	public UserEntity findByEmail(@Param("email") String email);
	
	@EntityGraph(value="authorization", type=EntityGraphType.FETCH)
	public UserEntity getUserWithAuthByEmail(String email);

}
