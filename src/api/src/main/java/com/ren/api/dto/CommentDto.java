package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ren.api.domain.Answer;
import com.ren.api.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentDto {

    public Long id;

    @Size(min = 10, max = 3000)
    public String text;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn;

    @NotNull
    public Answer answerId;

    @NotNull
    public User creatorId;
}
