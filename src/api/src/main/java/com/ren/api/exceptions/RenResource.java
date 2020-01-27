package com.ren.api.exceptions;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RenResource {

    private String code;

    private List<String> errorMessages;
}