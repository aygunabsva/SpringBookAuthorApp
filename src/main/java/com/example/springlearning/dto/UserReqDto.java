package com.example.springlearning.dto;

import com.example.springlearning.entity.Authority;
import com.example.springlearning.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserReqDto {
    private Long id;
    private String name;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private Set<Authority> authorities = new HashSet<>();
}
