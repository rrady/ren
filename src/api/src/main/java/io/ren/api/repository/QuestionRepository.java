package io.ren.api.repository;

import io.ren.api.domain.Question;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aneagu on 20/11/2019
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
