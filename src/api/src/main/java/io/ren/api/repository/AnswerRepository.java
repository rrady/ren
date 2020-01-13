package io.ren.api.repository;

import io.ren.api.domain.Answer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aneagu on 03/01/2020.
 */
public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
