package com.example.demo.web.converter;

import com.example.demo.domain.entity.User;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User userSaveDtoToEntity(UserSaveDto userSaveDto) {
        return User.builder()
                .address(userSaveDto.getAddress())
                .email(userSaveDto.getEmail())
                .password(userSaveDto.getPassword())
                .userId(userSaveDto.getUserId())
                .build();
    }

    public void update(User user, UserUpdateDto userUpdateDto) {
        user.setUserId(userUpdateDto.getUserId());
        user.setPassword(userUpdateDto.getPassword());
        user.setAddress(userUpdateDto.getAddress());
        user.setEmail(userUpdateDto.getEmail());
    }

    public UserResponseDto entityToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .id(user.getId())
                .address(user.getAddress())
                .password(user.getPassword())
                .userId(user.getUserId())
                .build();
    }
}
