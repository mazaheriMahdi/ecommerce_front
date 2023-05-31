package com.example.store_front.Exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException() {
        super("Login failed");
    }
}
