package com.example.demo.web.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.IdResponseDto;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserResponseDto findById(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User not found");
        });
        return userConverter.entityToUserResponseDto(user);
    }

    public IdResponseDto save(UserSaveDto userSaveDto) {
        User user = userConverter.userSaveDtoToEntity(userSaveDto);
        User savedUser = userRepository.save(user);
        return IdResponseDto.builder().id(savedUser.getId()).build();
    }
}
