package com.example.demo.web.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {
    private String userId;
    private String password;
    private String email;
    private String address;
}
