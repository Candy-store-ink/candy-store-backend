package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class NotValidImageException extends RuntimeException {

    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    private String message = "Not valid image";

    public NotValidImageException() {
        super("Not valid image");
    }

    public NotValidImageException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
