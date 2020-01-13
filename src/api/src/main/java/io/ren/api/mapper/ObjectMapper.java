package io.ren.api.mapper;


import io.ren.api.domain.*;
import io.ren.api.dto.*;

/**
 * Created by aneagu on 03/01/2020.
 */
public interface ObjectMapper {

    Question convertQuestionDtoToQuestion(final QuestionDto questionDto);

    QuestionDto convertQuestionToQuestionDto(final Question question);

    Comment convertCommentDtoToComment(final CommentDto commentDto);

    CommentDto convertCommentToCommentDto(final Comment comment);

    Answer convertAnswerDtoToAnswer(final AnswerDto answerDto);

    AnswerDto convertAnswerToAnswerDto(final Answer answer);

    Tag convertTagDtoToTag(final TagDto tagDto);

    TagDto convertTagToTagDto(final Tag tag);

    User convertUserDtoToUser(final UserDto userDto);

    UserDto convertUserToUserDto(final User user);

}
