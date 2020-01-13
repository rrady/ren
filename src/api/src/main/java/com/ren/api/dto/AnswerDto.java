package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AnswerDto {

    public Long id;

    @Size(min = 10, max = 3000)
    @NotNull
    public String text;

    @Size(min = 0)
    @NotNull
    public Integer rating;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date editedOn;

    public Set<Long> commentIds;
}
