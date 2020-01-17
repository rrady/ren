package com.ren.api.service;

import com.ren.api.domain.Question;
import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.NotFoundException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void update(Long id, QuestionDto questionDto) {
        Optional<Question> optional = questionRepository.findById(id);
        if (optional.isPresent()) {
            Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
            questionRepository.save(question);
        } else {
            throw new NotFoundException("Entity: " + Question.class + " not found!");
        }
    }

    @Override
    public List<QuestionDto> findAll() {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionRepository.findAll().forEach(question -> questionDtoList.add(objectMapper.convertQuestionToQuestionDto(question)));

        return questionDtoList;
    }

    @Override
    public Page<QuestionDto> getQuestionsPaginated(Pageable pageable) {
        return questionRepository.findAll(pageable)
                .map(objectMapper::convertQuestionToQuestionDto);
    }

    @Override
    public void save(QuestionDto questionDto) {
        Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
        questionRepository.save(question);
    }
}
