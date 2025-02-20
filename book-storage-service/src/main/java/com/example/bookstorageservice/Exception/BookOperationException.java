package com.example.bookstorageservice.Exception;

public class BookOperationException extends RuntimeException {
    public BookOperationException(String message) {
        super(message);
    }
}
