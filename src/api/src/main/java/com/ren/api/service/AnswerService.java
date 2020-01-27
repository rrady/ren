package com.ren.api.service;

import java.util.List;

import com.ren.api.dto.AnswerDto;
import com.ren.api.exceptions.RenException;

public interface AnswerService {
    void save(AnswerDto answerDto);

    void update(Long id, AnswerDto answerDto) throws RenException;

    List<AnswerDto> findAllByQuestionId(Long questionId);

    void delete(Long id) throws RenException;
}
