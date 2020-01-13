package io.ren.api.mapper;

import io.ren.api.domain.*;
import io.ren.api.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by aneagu on 03/01/2020.
 */
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
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public Answer convertAnswerDtoToAnswer(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);

    }

    @Override
    public AnswerDto convertAnswerToAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);

    }

    @Override
    public Tag convertTagDtoToTag(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);

    }

    @Override
    public TagDto convertTagToTagDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);

    }

    @Override
    public User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @Override
    public UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);

    }
}
