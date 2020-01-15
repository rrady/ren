package com.ren.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagDto {

    public Long id;

    @Size(min = 3, max = 30)
    public String text;
}
