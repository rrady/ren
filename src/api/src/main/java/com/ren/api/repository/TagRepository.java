package com.ren.api.repository;

import com.ren.api.domain.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t where t.id IN :list")
    Iterable<Tag> findAllByIds(@Param("list") List<Long> tagIds);

}