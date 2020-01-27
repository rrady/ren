package com.ren.api.service;

import com.ren.api.domain.Question;
import com.ren.api.dto.QuestionDto;
import com.ren.api.exceptions.Codes;
import com.ren.api.exceptions.RenException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public void update(Long id, QuestionDto questionDto) throws RenException {
        Optional<Question> optional = questionRepository.findById(id);
        if (optional.isPresent()) {
            Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
            question.setId(id);
            questionRepository.save(question);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Question with id: '%s' was not found.", id));
        }
    }

    @Override
    public Page<QuestionDto> getQuestions(String searchKey, Pageable pageable) {
        return (searchKey != null && !searchKey.isEmpty()) ? questionRepository.findAllByTitle(searchKey, pageable).map(objectMapper::convertQuestionToQuestionDto) :
                questionRepository.findAll(pageable).map(objectMapper::convertQuestionToQuestionDto);
    }

    @Override
    public void save(QuestionDto questionDto) {
        Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
        questionRepository.save(question);
    }

    @Override
    public QuestionDto getQuestionById(Long id) throws RenException {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            QuestionDto questionDto = objectMapper.convertQuestionToQuestionDto(optionalQuestion.get());
            return questionDto;
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Question with id: '%s' was not found.", id));
        }
    }

    @Override
    public void deleteQuestion(Long id) throws RenException {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            questionRepository.deleteById(id);
        } else {
            throw new RenException(Codes.RESOURCE_NOT_FOUND, String.format("Question with id: '%s' was not found.", id));
        }
    }
}
