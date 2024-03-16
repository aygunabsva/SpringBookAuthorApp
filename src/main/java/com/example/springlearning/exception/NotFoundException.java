package com.example.springlearning.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String ex) {
        super(ex);
    }
}
