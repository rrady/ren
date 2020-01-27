package com.ren.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ren.api.domain.Answer;
import com.ren.api.dto.AnswerDto;
import com.ren.api.exceptions.Codes;
import com.ren.api.exceptions.RenException;

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
    public void update(Long id, AnswerDto answerDto) throws RenException {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            Answer answer = objectMapper.convertAnswerDtoToAnswer(answerDto);
            answer.setId(id);
            answer.setEditedOn(new Date());
            answerRepository.save(answer);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Answer with id: '%s' was not found.", id));
        }
    }

    @Override
    public List<AnswerDto> findAllByQuestionId(Long questionId) {
        List<AnswerDto> resultList = new ArrayList<>();
        answerRepository.findAllByQuestionId(questionId)
            .forEach(answer -> resultList.add(objectMapper.convertAnswerToAnswerDto(answer)));
            
        return resultList;
    }

    @Override
    public void delete(Long id) throws RenException {
        Optional<Answer> optional = answerRepository.findById(id);
        if (optional.isPresent()) {
            answerRepository.deleteById(id);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Answer with id: '%s' was not found.", id));
        }
    }
}
