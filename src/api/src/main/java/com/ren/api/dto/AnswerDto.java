package com.ren.api.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AnswerDto {

    public Long id;

    @Size(min = 10, max = 3000, message = "Text must be between 10 and 3000.")
    @NotNull(message = "Text must not be empty.")
    public String text;

    @NotNull(message = "Rating can't be empty.")
    public Integer rating = 0;

    @NotNull(message = "Date can't be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date editedOn;

    public Long userId;

    public String userName;

    public Long questionId;

    public Set<CommentDto> comments;

}
