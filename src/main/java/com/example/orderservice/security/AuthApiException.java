package com.example.orderservice.security;

import org.springframework.http.HttpStatus;

public class AuthApiException extends RuntimeException{
    public AuthApiException(HttpStatus status,String message){
        //System.out.println(status);
        super(message);
    }
}
