package com.ren.api.service;

import com.ren.api.dto.AnswerDto;

/**
 * Created by aneagu on 13/01/2020.
 */
public interface AnswerService {
    void createAnswer(AnswerDto answerDto, Long questionId, Long creatorId);
}
