package com.ren.api.repository;

import com.ren.api.domain.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by aneagu on 03/01/2020.
 */
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.id IN :ids")
    Iterable<Answer> findAllByIds(List<Long> ids);

    @Query("SELECT a FROM Answer a, Question b WHERE a.question.id = b.id AND a.question.id = :questionId")
    Iterable<Answer> findAllByQuestionId(Long questionId);
}
