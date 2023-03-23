package com.example.demo;

import com.example.demo.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

public class RandomUserFactory {
    public static User createRandomUuidUser() {
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .address(UUID.randomUUID().toString())
                .email(UUID.randomUUID().toString())
                .password(UUID.randomUUID().toString())
                .build();
    }
}
