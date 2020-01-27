package com.ren.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordModel {
    private Long userId;

    private String currentPassword;

    private String newPassword;
}