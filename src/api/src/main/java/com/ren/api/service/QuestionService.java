package com.ren.api.service;

import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.RenException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QuestionService {
    void save(QuestionDto questionDto);

    void update(Long id, QuestionDto questionDto) throws RenException;

    Page<QuestionDto> getQuestions(String searchKey, Pageable pageable);

    QuestionDto getQuestionById(Long id) throws RenException;

    void deleteQuestion(Long id) throws RenException;
}
