package com.example.demo;

import com.example.demo.domain.entity.User;
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
}
