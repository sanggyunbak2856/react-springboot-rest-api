package com.example.demo.web.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.IdResponseDto;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Transactional
    public IdResponseDto save(UserSaveDto userSaveDto) {
        User user = userConverter.userSaveDtoToEntity(userSaveDto);
        User savedUser = userRepository.save(user);
        return IdResponseDto.builder().id(savedUser.getId()).build();
    }

    @Transactional
    public IdResponseDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User not found");
        });
        userConverter.update(user, userUpdateDto);
        return IdResponseDto.builder().id(user.getId()).build();
    }

    @Transactional
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("User not found");
        });
        return userConverter.entityToUserResponseDto(user);
    }

    @Transactional
    public IdResponseDto deleteById(Long id) {
        userRepository.deleteById(id);
        return IdResponseDto.builder().id(id).build();
    }
}
