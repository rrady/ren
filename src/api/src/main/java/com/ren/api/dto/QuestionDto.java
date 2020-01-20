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

    @NotNull
    @Size(min = 15, max = 3000)
    public String text;

    @NotNull
    @Size(min = 4, max = 150)
    public String title;

    public Integer viewCount = 0;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn = new Date();

    @NotNull
    public Long userId;

    public String userName;

    public Set<TagDto> tags;

    public Set<AnswerDto> answers;
}
