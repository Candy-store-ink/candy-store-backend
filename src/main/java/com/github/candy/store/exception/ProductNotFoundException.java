package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseHttpException {

    public ProductNotFoundException() {
        super("Product not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    @Override
    protected HttpStatus defaultHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    protected String defaultMessage() {
        return "Product not found";
    }
}
