package com.example.demo;

import com.example.demo.domain.entity.Category;
import com.example.demo.domain.entity.Item;
import com.github.javafaker.Faker;

public class RandomItemFactory {
    private static Faker faker = new Faker();

    public static Item createRandomItem() {
        return Item.builder()
                .category(Category.ELECTRONIC)
                .name(faker.name().name())
                .price(faker.random().nextInt(1000, 100000))
                .quantity(faker.random().nextInt(0, 2000))
                .build();
    }
}
