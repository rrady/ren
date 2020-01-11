package com.ren.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.ren.api.domain.entities.Question;
import com.ren.api.dto.QuestionDto;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createQuestion(@NonNull QuestionDto questionDto) {
        Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
        questionRepository.save(question);
    }

    @Override
    public List<QuestionDto> findAll() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionRepository.findAll()
                .forEach(question -> questionDtoList.add(objectMapper.convertQuestionToQuestionDto(question)));

        return questionDtoList;
    }
}