package com.ren.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ren.api.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("SELECT a FROM Answer a, Comment b WHERE a.id = b.answer.id AND a.id = :answerId")
    Iterable<Comment> findAllByAnswerId(Long answerId);
}