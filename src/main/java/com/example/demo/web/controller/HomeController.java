package com.example.demo.web.controller;

import com.example.demo.web.dto.user.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return "home/home";
        }

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("loginUser");
        if(userResponseDto == null) {
            return "home/home";
        }

        model.addAttribute("userResponseDto", userResponseDto);
        return "home/loginHome";
    }
}
