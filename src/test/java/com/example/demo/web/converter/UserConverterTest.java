package com.example.demo.web.converter;

import com.example.demo.RandomUserFactory;
import com.example.demo.domain.entity.User;
import com.example.demo.web.dto.user.UserResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class UserConverterTest {
    UserConverter userConverter = new UserConverter();

    @Test
    void User엔티티를_UserResponseDto로_변환한다() {
        // given
        User userA = RandomUserFactory.createRandomUser();

        // when
        UserResponseDto userResponseDto = userConverter.entityToUserResponseDto(userA);

        // then
        assertThat(userA.getUserId()).isEqualTo(userResponseDto.getUserId());
        assertThat(userA.getEmail()).isEqualTo(userResponseDto.getEmail());
        assertThat(userA.getPassword()).isEqualTo(userResponseDto.getPassword());
        assertThat(userA.getAddress()).isEqualTo(userResponseDto.getAddress());
    }

    @Test
    void UserSaveDto를_User엔티티로_변환한다() {
        // given
        UserSaveDto userSaveDto = RandomUserFactory.createRandomUserSaveDto();

        // when
        User user = userConverter.userSaveDtoToEntity(userSaveDto);

        // then
        assertThat(userSaveDto.getUserId()).isEqualTo(user.getUserId());
        assertThat(userSaveDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userSaveDto.getAddress()).isEqualTo(user.getAddress());
        assertThat(userSaveDto.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void User엔티티를_UserUpdateDto로_수정한다() {
        // given
        User user = RandomUserFactory.createRandomUser();
        UserUpdateDto userUpdateDto = RandomUserFactory.createRandomUserUpdateDto();

        // when
        userConverter.userUpdate(user, userUpdateDto);

        // then
        assertThat(user.getUserId()).isEqualTo(userUpdateDto.getUserId());
        assertThat(user.getPassword()).isEqualTo(userUpdateDto.getPassword());
        assertThat(user.getAddress()).isEqualTo(userUpdateDto.getAddress());
        assertThat(user.getEmail()).isEqualTo(userUpdateDto.getEmail());
    }
}