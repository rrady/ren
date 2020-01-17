package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AnswerDto {

    public Long id;

    @Size(min = 10, max = 3000)
    @NotNull
    public String text;

    @NotNull
    public Integer rating;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date editedOn;

    public UserDto user;

    public QuestionDto question;

    public Set<CommentDto> comments;

}
