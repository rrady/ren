package com.ren.api.service;

import com.ren.api.dto.AnswerDto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by aneagu on 13/01/2020.
 */
public interface AnswerService {
    void save(AnswerDto answerDto);

    void update(Long id, AnswerDto answerDto);

    List<AnswerDto> findAllByQuestionId(Long questionId);

}
