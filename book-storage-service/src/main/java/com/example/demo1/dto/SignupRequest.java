package com.example.demo1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignupRequest {
    private String username;
    private String password;
}
