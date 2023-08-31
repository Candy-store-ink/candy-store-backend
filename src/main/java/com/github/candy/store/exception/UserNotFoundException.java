package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseHttpException {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    @Override
    protected HttpStatus defaultHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    protected String defaultMessage() {
        return "User not found";
    }
}
