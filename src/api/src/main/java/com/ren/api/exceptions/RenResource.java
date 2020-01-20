package com.ren.api.exceptions;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class RenResource {
    private String code;
    private String message;
}