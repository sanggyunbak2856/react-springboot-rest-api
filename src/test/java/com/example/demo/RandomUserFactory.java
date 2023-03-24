package com.example.demo;

import com.example.demo.domain.entity.User;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class RandomUserFactory {
    private static Faker faker = new Faker();

    public static User createRandomUser() {
        return User.builder()
                .userId(faker.name().name())
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static UserSaveDto createRandomUserSaveDto() {
        return UserSaveDto.builder()
                .userId(faker.name().name())
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static UserUpdateDto createRandomUserUpdateDto() {
        return UserUpdateDto.builder()
                .address(faker.address().fullAddress())
                .userId(faker.name().name())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }
}
