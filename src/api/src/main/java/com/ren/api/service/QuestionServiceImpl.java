package com.ren.api.service;

import com.ren.api.domain.Question;
import com.ren.api.domain.Tag;
import com.ren.api.dto.QuestionDto;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by aneagu on 08/01/2020.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final TagService tagService;

    private final ObjectMapper objectMapper;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, TagService tagService, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.tagService = tagService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createQuestion(QuestionDto questionDto, List<Long> tagIds) {
        Question question = objectMapper.convertQuestionDtoToQuestion(questionDto);
        Set<Tag> tags = tagService.findAllByIds(tagIds)
                .stream().map(objectMapper::convertTagDtoToTag)
                .collect(Collectors.toSet());
        question.setTags(tags);
        questionRepository.save(question);
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
}
