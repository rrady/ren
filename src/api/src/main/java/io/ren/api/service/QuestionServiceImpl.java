package io.ren.api.service;

import io.ren.api.aop.EnteringExitingLog;
import io.ren.api.domain.Question;
import io.ren.api.dto.QuestionDto;
import io.ren.api.mapper.ObjectMapper;
import io.ren.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aneagu on 08/01/2020.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    @EnteringExitingLog
    @Override
    public void createQuestion(@NonNull QuestionDto questionDto) {
        Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
        questionRepository.save(question);
    }

    @EnteringExitingLog
    @Override
    public List<QuestionDto> findAll() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionRepository.findAll().forEach(question -> questionDtoList.add(objectMapper.convertQuestionToQuestionDto(question)));

        return questionDtoList;
    }

    @EnteringExitingLog
    @Override
    public Page<QuestionDto> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(objectMapper::convertQuestionToQuestionDto);
    }
}
