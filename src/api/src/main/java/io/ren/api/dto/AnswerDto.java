package io.ren.api.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerDto {
    public Long id;

    public String text;

    public Integer rating;

    public Date createdOn;

    public Date editedOn;

    public Long creatorId;

    public Long questionId;

    public Set<Long> commentIds;
}
