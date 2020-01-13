package com.ren.api.service;

import com.ren.api.domain.User;
import com.ren.api.dto.UserDto;
import com.ren.api.exceptions.NotFoundException;
import com.ren.api.mapper.ObjectMapper;
import com.ren.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by aneagu on 13/01/2020.
 */
@Service
public class UserServiceImpl implements UserService {

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return objectMapper.convertUserToUserDto(userOptional.orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!")));
    }
}
