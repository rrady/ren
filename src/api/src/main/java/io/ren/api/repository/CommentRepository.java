package io.ren.api.repository;

import io.ren.api.domain.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Long> {
}
