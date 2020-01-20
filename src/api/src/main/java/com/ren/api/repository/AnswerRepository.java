package com.ren.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ren.api.domain.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.id IN :ids")
    Iterable<Answer> findAllByIds(List<Long> ids);

    @Query("SELECT a FROM Answer a, Question b WHERE a.question.id = b.id AND a.question.id = :questionId")
    Iterable<Answer> findAllByQuestionId(Long questionId);
}
