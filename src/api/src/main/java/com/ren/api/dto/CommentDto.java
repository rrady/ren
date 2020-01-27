package com.ren.api.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommentDto {

    public Long id;

    @Size(min = 10, max = 3000, message = "Text must be between 10 and 3000.")
    public String text;

    @NotNull(message = "Date can't be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date createdOn = new Date();

    public Long answerId;

    public Long creatorId;

    public String creatorName;
}
