package com.ren.api.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private String id;
    private String title;
    private String description;
    private String body;
    private Date createdAt;
    private Date updatedAt;
    private List<Long> tagList;
}