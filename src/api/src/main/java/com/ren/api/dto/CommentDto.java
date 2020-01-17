package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by aneagu on 03/01/2020.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommentDto {

    public Long id;

    @Size(min = 10, max = 3000)
    public String text;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn;

    @NotNull
    public AnswerDto answer;

    @NotNull
    public UserDto user;
}
