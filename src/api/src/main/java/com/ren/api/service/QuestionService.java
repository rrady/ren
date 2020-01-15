package com.ren.api.service;

import com.ren.api.dto.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aneagu on 08/01/2020.
 */
public interface QuestionService {
    void createQuestion(QuestionDto questionDto, List<Long> tagIds);

    List<QuestionDto> findAll();

    Page<QuestionDto> getQuestionsPaginated(Pageable pageable);
}
