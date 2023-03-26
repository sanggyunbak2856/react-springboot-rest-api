package com.example.demo.web.service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.user.UserLoginDto;
import com.example.demo.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserResponseDto login(UserLoginDto userLoginDto) {
        User foundUser = userRepository.findUserByUserId(userLoginDto.getUserId());
        if(foundUser == null) return null;
        else {
            if(!foundUser.getPassword().equals(userLoginDto.getPassword())) return null;
            return userConverter.entityToUserResponseDto(foundUser);
        }
    }
}
