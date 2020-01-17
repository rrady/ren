package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class QuestionDto {

    public Long id;

    @NotNull
    @Size(min = 15, max = 3000)
    public String text;

    @NotNull
    @Size(min = 4, max = 150)
    public String title;

    public Integer viewCount;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn;

    @NotNull
    public UserDto user;

    public Set<TagDto> tags;

    public Set<AnswerDto> answers;
}
