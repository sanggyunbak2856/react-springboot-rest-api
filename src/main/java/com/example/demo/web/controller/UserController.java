package com.example.demo.web.controller;

import com.example.demo.web.dto.IdResponseDto;
import com.example.demo.web.dto.user.UserSaveDto;
import com.example.demo.web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String addUser(@ModelAttribute UserSaveDto userSaveDto) {
        return "user/addUser";
    }

    @PostMapping("/user")
    public String saveUser(@Validated @ModelAttribute UserSaveDto userSaveDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/user/addUser";
        }

        try {
            userService.save(userSaveDto);
        } catch (IllegalArgumentException e) {
            bindingResult.reject("user", e.getMessage());
            return "/user/addUser";
        }

        return "/home/home";
    }
}
