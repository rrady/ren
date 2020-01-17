package com.ren.api.service;

import com.ren.api.dto.CommentDto;

import java.util.List;

/**
 * Created by aneagu on 14/01/2020.
 */
public interface CommentService {
    void save(CommentDto commentDto);

    void update(Long id, CommentDto commentDto);

    List<CommentDto> findAllByAnswerId(Long answerId);
}
