package com.ren.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by aneagu on 03/01/2020.
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
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
