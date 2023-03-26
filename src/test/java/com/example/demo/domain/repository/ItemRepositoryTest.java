package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Item;
import com.example.demo.domain.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.demo.DummyObjectFactory.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    User userA;
    Item itemA;

    @BeforeEach
    void beforeEach() {
        userA = createUser();
        itemA = createItem();
        itemA.setUser(userA);
    }

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    void 아이템을_저장하고_조회한다() {
        // given
        userRepository.save(userA);

        // when
        Item savedItem = itemRepository.save(itemA);

        // then
        Item foundItem = itemRepository.findById(savedItem.getId()).get();
        assertThat(savedItem).usingRecursiveComparison().isEqualTo(foundItem);
    }

    @Test
    @Transactional
    void 아이템을_수정한다() {
        // given
        userRepository.save(userA);
        itemRepository.save(itemA);

        // when
        itemA.setName("itemB");

        // then
        assertThat(itemA.getName()).isEqualTo("itemB");
    }

    @Test
    @Transactional
    void 아이템을_삭제한다() {
        // given
        userRepository.save(userA);
        Item savedItem = itemRepository.save(itemA);

        // when
        itemRepository.deleteById(savedItem.getId());

        // then
        Optional<Item> foundItem = itemRepository.findById(savedItem.getId());
        assertThat(foundItem).isEmpty();
    }
}
