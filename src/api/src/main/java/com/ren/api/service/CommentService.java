package com.ren.api.service;

import java.util.List;

import com.ren.api.dto.CommentDto;
import com.ren.api.exceptions.RenException;

public interface CommentService {
    void save(CommentDto commentDto);

    void update(Long id, CommentDto commentDto) throws RenException;

    List<CommentDto> findAllByAnswerId(Long answerId);

    void delete(Long id) throws RenException;
}
