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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class QuestionDto {

    public Long id;

    @NotNull(message = "Text must not be empty.")
    @Size(min = 15, max = 3000, message = "Text must be between 15 and 3000.")
    public String text;

    @NotNull(message = "Title must not be empty.")
    @Size(min = 4, max = 150, message = "Title must be between 4 and 150.")
    public String title;

    public Integer viewCount = 0;

    @NotNull(message = "Date must not be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn = new Date();

    @NotNull(message = "User id can't be empty.")
    public Long userId;

    public String userName;

    public Set<TagDto> tags;

    public Set<AnswerDto> answers;
}
