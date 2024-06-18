package com.example.blackListService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlackListAllReadyExistsException extends RuntimeException {

    public BlackListAllReadyExistsException(String message) {
        super(message);
    }

}
