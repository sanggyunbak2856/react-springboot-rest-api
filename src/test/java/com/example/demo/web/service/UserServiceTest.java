package com.example.demo.web.service;

import com.example.demo.RandomUserFactory;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.web.converter.UserConverter;
import com.example.demo.web.dto.IdResponseDto;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        UserSaveDto userSaveDto = RandomUserFactory.createRandomUserSaveDto();
        User user = userConverter.userSaveDtoToEntity(userSaveDto);
        user.setId(1L);
        when(userRepository.save(any())).thenReturn(user);

        // when
        IdResponseDto savedId = userService.save(userSaveDto);

        // then
        assertThat(savedId.getId()).isEqualTo(user.getId());
    }

    @Test
    void 저장된_유저를_조회한다() {
        // given
        User user = RandomUserFactory.createRandomUser();
        user.setId(1L);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        // when
        UserResponseDto responseDto = userService.findById(1L);

        // then
        assertThat(responseDto.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    void 잘못된_유저_번호로_유저를_조회하여_예외가_발생한다() {
        // given
        UserSaveDto userSaveDto = RandomUserFactory.createRandomUserSaveDto();
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> {
            userService.findById(1L);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("User not found");
    }

}