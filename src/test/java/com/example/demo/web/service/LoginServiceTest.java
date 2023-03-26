package com.example.demo.web.service;

import com.example.demo.DummyObjectFactory;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.user.UserLoginDto;
import com.example.demo.web.dto.user.UserResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.DummyObjectFactory.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserConverter userConverter;

    @InjectMocks
    LoginService loginService;

    @Test
    void 로그인_성공_테스트() {
        // given
        User user = createUser();
        when(userRepository.findUserByUserId(any())).thenReturn(user);
        UserResponseDto userResponseDto = userConverter.entityToUserResponseDto(user);
        UserLoginDto loginDto = UserLoginDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .build();

        // when
        UserResponseDto loginedUser = loginService.login(loginDto);

        // then
        then(userRepository).should().findUserByUserId(any());
        assertThat(userResponseDto).usingRecursiveComparison().isEqualTo(userResponseDto);
    }

    @Test
    void 비밀번호가_달라_NUll이_반환되는_테스트() {
        // given
        User user = createUser();
        when(userRepository.findUserByUserId(any())).thenReturn(user);
        UserLoginDto loginDto = UserLoginDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword() + "101")
                .build();

        // when
        UserResponseDto nullResponseDto = loginService.login(loginDto);

        // then
        assertThat(nullResponseDto).isNull();
    }
}