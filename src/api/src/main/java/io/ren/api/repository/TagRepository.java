package io.ren.api.repository;

import io.ren.api.domain.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by aneagu on 20/11/2019
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
}