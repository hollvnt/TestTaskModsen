package com.example.booktrackerservice.Model;

import lombok.*;

@AllArgsConstructor
@Getter
public enum BookStatusEnum {
    AVAILABLE("available"),
    TAKEN("taken");

    private final String status;
}
