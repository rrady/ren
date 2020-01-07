package io.ren.api.mapper;

import io.ren.api.domain.*;
import io.ren.api.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public Question convertQuestionDtoToQuestion(final QuestionDto questionDto) {
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
        return null;
    }

    @Override
    public Answer convertAnswerDtoToAnswer(AnswerDto answerDto) {
        return null;
    }

    @Override
    public AnswerDto convertAnswerToAnswerDto(Answer answer) {
        return null;
    }

    @Override
    public Tag convertTagDtoToTag(TagDto tagDto) {
        return null;
    }

    @Override
    public TagDto convertTagToTagDto(Tag tag) {
        return null;
    }

    @Override
    public User convertUserDtoToUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto convertUserToUserDto(User user) {
        return null;
    }
}
