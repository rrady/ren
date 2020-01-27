package com.ren.api.dto;

import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class TagDto {

    public Long id;

    @Size(min = 3, max = 30, message = "Text must be between 3 and 30.")
    public String text;

    @JsonIgnore
    public Set<QuestionDto> questions;
}
