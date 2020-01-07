package io.ren.api.service;

import io.ren.api.domain.Question;
import io.ren.api.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    void createQuestion(QuestionDto questionDto);
    List<QuestionDto> findAll();
}
