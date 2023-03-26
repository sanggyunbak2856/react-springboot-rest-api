package com.example.demo;

import com.example.demo.domain.entity.Category;
import com.example.demo.domain.entity.Item;
import com.example.demo.domain.entity.User;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import com.github.javafaker.Faker;

public class DummyObjectFactory {
    private static Faker faker = new Faker();

    public static User createUser() {
        return User.builder()
                .userId(faker.name().name())
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static UserSaveDto createUserSaveDto() {
        return UserSaveDto.builder()
                .userId(faker.name().name())
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static UserUpdateDto createUserUpdateDto() {
        return UserUpdateDto.builder()
                .userId(faker.name().name())
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static Item createItem() {
        return Item.builder()
                .category(Category.ELECTRONIC)
                .name(faker.name().name())
                .price(faker.random().nextInt(1000, 100000))
                .quantity(faker.random().nextInt(0, 2000))
                .build();
    }
}
