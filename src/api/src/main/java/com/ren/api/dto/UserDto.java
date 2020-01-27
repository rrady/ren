package com.ren.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserDto {

    private Long id;

    @NotNull(message = "Email can't be empty.")
    @Email
    private String email;

    @NotNull(message = "Name can't be empty.")
    @Size(min = 3, max = 15)
    private String name;
}
