package com.ren.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ren.api.domain.entities.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}