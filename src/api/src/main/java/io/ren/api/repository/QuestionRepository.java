package io.ren.api.repository;

import io.ren.api.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aneagu on 03/01/2020.
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);
}
