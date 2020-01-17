package com.ren.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by aneagu on 03/01/2020.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserDto {

    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    private String image;
}
