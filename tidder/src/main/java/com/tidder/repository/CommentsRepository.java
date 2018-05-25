package com.tidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tidder.model.CommentEntity;

@Repository("commentsRepository")
public interface CommentsRepository extends JpaRepository<CommentEntity, Integer> {

}
