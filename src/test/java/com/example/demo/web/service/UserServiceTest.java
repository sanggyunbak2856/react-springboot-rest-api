package com.example.demo.web.service;

import com.example.demo.DummyObjectFactory;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.demo.DummyObjectFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Spy
    UserConverter userConverter;

    @InjectMocks
    UserService userService;

    @Test
    void 유저를_저장한다() {
        // given
        UserSaveDto userSaveDto = createUserSaveDto();
        User user = userConverter.userSaveDtoToEntity(userSaveDto);
        when(userRepository.save(any())).thenReturn(user);

        // when
        userService.save(userSaveDto);

        // then
        then(userRepository).should().save(any());
    }

    @Test
    void 유저를_조회한다() {
        // given
        User user = createUser();
        user.setId(1L);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // when
        userService.findById(1L);

        // then
        then(userRepository).should().findById(any());
    }

    @Test
    void 유저를_수정한다() {
        // given
        User user = createUser();
        user.setId(1L);
        UserUpdateDto userUpdateDto = createUserUpdateDto();
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // when
        userService.update(1L, userUpdateDto);

        // then
        then(userRepository).should().findById(any());
    }
}