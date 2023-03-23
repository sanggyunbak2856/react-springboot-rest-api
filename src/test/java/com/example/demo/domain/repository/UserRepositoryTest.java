package com.example.demo.domain.repository;

import com.example.demo.RandomUserFactory;
import com.example.demo.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    void 유저를_저장하고_조회한다() {
        // given
        User userA = RandomUserFactory.createRandomUuidUser();

        // when
        User savedUser = userRepository.save(userA);

        // then
        User foundUser = userRepository.findById(savedUser.getId()).get();
        assertThat(foundUser).usingRecursiveComparison().isEqualTo(savedUser);
    }

    @Test
    @Transactional
    void 유저를_수정한다() {
        // given
        User userA = RandomUserFactory.createRandomUuidUser();
        User savedUser = userRepository.save(userA);

        // when
        savedUser.setAddress("대전 유성구 궁동");
        savedUser.setEmail("helloworld@hello.world");

        // then
        User foundUser = userRepository.findById(savedUser.getId()).get();
        assertThat(foundUser.getAddress()).isEqualTo("대전 유성구 궁동");
        assertThat(foundUser.getEmail()).isEqualTo("helloworld@hello.world");
    }

    @Test
    @Transactional
    void 유저를_삭제한다() {
        // given
        User userA = RandomUserFactory.createRandomUuidUser();
        User savedUser = userRepository.save(userA);

        // when
        userRepository.deleteById(savedUser.getId());

        // then
        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isEmpty();
    }
}