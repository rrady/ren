package io.ren.api.repository.optional;

import io.ren.api.domain.optional.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {

}
