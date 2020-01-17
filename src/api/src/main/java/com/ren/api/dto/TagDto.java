package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by aneagu on 03/01/2020.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class TagDto {

    public Long id;

    public String name;

    @Size(min = 3, max = 30)
    public String text;

    public Set<QuestionDto> questions;
}
