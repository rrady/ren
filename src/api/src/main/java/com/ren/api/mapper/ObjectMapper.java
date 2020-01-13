package com.ren.api.mapper;

import com.ren.api.domain.*;
import com.ren.api.dto.*;

public interface ObjectMapper {

    Question convertQuestionDtoToQuestion(final QuestionDto questionDto);

    QuestionDto convertQuestionToQuestionDto(final Question question);

    Comment convertCommentDtoToComment(final CommentDto commentDto);

    CommentDto convertCommentToCommentDto(final Comment comment);

    Tag convertTagDtoToTag(final TagDto tagDto);

    TagDto convertTagToTagDto(final Tag tag);

    Answer convertAnswerDtoToAnswer(final AnswerDto answerDto);

    AnswerDto convertAnswerToAnswerDto(final Answer answer);

    User convertUserDtoToUser(final UserDto userDto);

    UserDto convertUserToUserDto(final User user);
}