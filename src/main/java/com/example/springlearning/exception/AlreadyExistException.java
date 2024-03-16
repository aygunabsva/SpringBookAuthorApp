package com.example.springlearning.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String msg) {
        super(msg);
    }
}
