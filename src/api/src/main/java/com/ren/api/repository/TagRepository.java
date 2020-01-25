package com.ren.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ren.api.domain.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

}