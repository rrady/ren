package com.ren.api.repository;

import com.ren.api.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("SELECT a FROM Answer a, Comment b WHERE a.id = b.answer.id AND a.id = :answerId")
    Iterable<Comment> findAllByAnswerId(Long answerId);




}