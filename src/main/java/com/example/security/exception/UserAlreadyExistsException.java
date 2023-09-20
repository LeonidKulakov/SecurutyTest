package com.example.security.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String massage){
        super(massage);
    }
}
