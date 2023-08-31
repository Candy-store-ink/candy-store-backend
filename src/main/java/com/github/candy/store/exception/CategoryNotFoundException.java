package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BaseHttpException {

    public CategoryNotFoundException() {
        super("Category not found");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    @Override
    protected HttpStatus defaultHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    protected String defaultMessage() {
        return "Category not found";
    }
}
