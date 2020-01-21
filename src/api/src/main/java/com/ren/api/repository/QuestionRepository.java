package com.ren.api.repository;

import com.ren.api.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    Page<Question> findAllByTitle(String searchKey, Pageable pageable);

    Page<Question> findAll(Pageable pageable);
}
