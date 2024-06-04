package com.example.blackListService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LuhnException extends RuntimeException{
    public LuhnException(String message) {
        super(message);
    }
}
