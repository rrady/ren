package io.ren.api.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionDto {

    public Long id;

    public String text;

    public String title;

    public Integer viewCount;

    public Date createdOn;

    public Long creatorId;

    public Set<Long> tagIds;

    public Set<Long> answerIds;

}
