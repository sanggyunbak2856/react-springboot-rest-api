package com.example.demo.web.converter;

import com.example.demo.domain.entity.User;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponseDto entityToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .address(user.getAddress())
                .email(user.getEmail())
                .build();
    }

    public User userSaveDtoToEntity(UserSaveDto userSaveDto) {
        return User.builder()
                .password(userSaveDto.getPassword())
                .userId(userSaveDto.getUserId())
                .email(userSaveDto.getEmail())
                .address(userSaveDto.getAddress())
                .build();
    }

    public void userUpdate(User user, UserUpdateDto userUpdateDto) {
        user.setEmail(userUpdateDto.getEmail());
        user.setUserId(userUpdateDto.getUserId());
        user.setAddress(userUpdateDto.getAddress());
        user.setPassword(userUpdateDto.getPassword());
    }
}
