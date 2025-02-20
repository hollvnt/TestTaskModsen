package com.example.bookstorageservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookStatusEnum {
    AVAILABLE("available"),
    TAKEN("taken");

    private final String status;
}