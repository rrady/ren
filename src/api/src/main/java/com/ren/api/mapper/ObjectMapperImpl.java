package com.ren.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.ren.api.domain.entities.*;
import com.ren.api.dto.*;

@Component
public class ObjectMapperImpl implements ObjectMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ObjectMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Question convertQuestionDtoToQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    @Override
    public QuestionDto convertQuestionToQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public Comment convertCommentDtoToComment(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }

    @Override
    public CommentDto convertCommentToCommentDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public Tag convertTagDtoToTag(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);
    }

    @Override
    public TagDto convertTagToTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }
}