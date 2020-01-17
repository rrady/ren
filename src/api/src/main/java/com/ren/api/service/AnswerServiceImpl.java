package com.ren.api.service;

import com.ren.api.domain.Answer;
import com.ren.api.domain.Question;
import com.ren.api.dto.AnswerDto;
import com.ren.api.exceptions.NotFoundException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by aneagu on 13/01/2020.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, ObjectMapper objectMapper) {
        this.answerRepository = answerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(AnswerDto answerDto) {
        answerRepository.save(objectMapper.convertAnswerDtoToAnswer(answerDto));
    }

    @Override
    public void update(Long id, AnswerDto answerDto) {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            Answer answer = objectMapper.convertAnswerDtoToAnswer(answerDto);
            answerRepository.save(answer);
        } else {
            throw new NotFoundException("Entity: " + Answer.class + " not found!");
        }
    }

    @Override
    public List<AnswerDto> findAllByQuestionId(Long questionId) {
        List<AnswerDto> resultList = new ArrayList<>();
        answerRepository.findAllByQuestionId(questionId)
                .forEach(answer -> resultList.add(objectMapper.convertAnswerToAnswerDto(answer)));
        return resultList;
    }
}
