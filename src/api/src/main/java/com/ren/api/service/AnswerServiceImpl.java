package com.ren.api.service;

import com.ren.api.domain.Answer;
import com.ren.api.dto.AnswerDto;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.AnswerRepository;
import com.ren.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aneagu on 13/01/2020.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionService questionService;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionService questionService, UserRepository userRepository, ObjectMapper objectMapper) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createAnswer(AnswerDto answerDto, Long questionId, Long creatorId) {
        Answer answer = objectMapper.convertAnswerDtoToAnswer(answerDto);
        //User user = objectMapper.convertUserDtoToUser(userRepository.findById(creatorId).get());
//       TODO: question & rest

    }
}
