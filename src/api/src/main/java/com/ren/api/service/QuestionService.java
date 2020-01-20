package com.ren.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.RenException;

public interface QuestionService {
    void save(QuestionDto questionDto);

    void update(Long id, QuestionDto questionDto) throws RenException;

    Page<QuestionDto> getQuestionsPaginated(Pageable pageable);
}
