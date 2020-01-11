package com.ren.api.service;

import java.util.List;

import com.ren.api.dto.QuestionDto;

public interface QuestionService {
    void createQuestion(QuestionDto questionDto);
    List<QuestionDto> findAll();
}