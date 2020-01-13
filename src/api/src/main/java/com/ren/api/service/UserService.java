package com.ren.api.service;

import com.ren.api.dto.UserDto;

/**
 * Created by aneagu on 13/01/2020.
 */
public interface UserService {
    UserDto findUserById(Long id);
}
