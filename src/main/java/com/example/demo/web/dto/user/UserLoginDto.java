package com.example.demo.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
