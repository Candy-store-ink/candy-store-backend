package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class NotValidImageException extends BaseHttpException {

    public NotValidImageException() {
        super("Not valid image");
    }

    public NotValidImageException(String message) {
        super(message);
    }

    public NotValidImageException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    @Override
    protected HttpStatus defaultHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    protected String defaultMessage() {
        return "Not valid image";

    }
}
