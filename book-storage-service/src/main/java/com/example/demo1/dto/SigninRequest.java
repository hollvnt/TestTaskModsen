package com.example.demo1.dto;  // Пакет для DTO-классов

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
