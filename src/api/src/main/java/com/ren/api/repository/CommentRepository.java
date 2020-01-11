package com.ren.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ren.api.domain.entities.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}