package com.github.candy.store.exception;

import org.springframework.http.HttpStatus;

/**
 * Base class for all HTTP exceptions.
 * <p>
 * All HTTP exceptions should extend this class.
 * This class provides default implementations for {@link #getHttpStatus()} and {@link #getMessage()} methods.
 * </p>
 * <p>
 * This class extends {@link RuntimeException} for catching exceptions in {@link ControllerAdvisor}.
 * <p>
 * This class is used by {@link ControllerAdvisor} to handle exceptions.
 * See {@link ControllerAdvisor} for more information.
 * </p>
 */
public abstract class BaseHttpException extends RuntimeException {

    /**
     * HTTP status code.
     * <p>
     * This field is used by {@link ControllerAdvisor} as a response status code.
     * If this field is null, {@link #defaultHttpStatus()} will be used.
     * See {@link ControllerAdvisor} for more information.
     * </p>
     */
    private HttpStatus httpStatus;
    /**
     * Error message.
     * <p>
     * This field is used by {@link ControllerAdvisor} as a response body.
     * If this field is null, {@link #defaultMessage()} will be used.
     * See {@link ControllerAdvisor} for more information.
     * </p>
     */
    private final String message;

    /**
     * Creates a new exception with specified message.
     */
    public BaseHttpException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Creates a new exception with specified message and HTTP status code.
     */
    public BaseHttpException(String message, HttpStatus httpStatus) {
        this(message);
        this.httpStatus = httpStatus;
    }

    /**
     * This method will be used in {@link #getHttpStatus()} if {@link #httpStatus} is null.
     */
    protected abstract HttpStatus defaultHttpStatus();

    /**
     * Returns HTTP status code.
     * <p>
     * This method is used by {@link ControllerAdvisor} as a response status code.
     * If {@link #httpStatus} is null, {@link #defaultHttpStatus()} will be used.
     * See {@link ControllerAdvisor} for more information.
     * </p>
     */
    public HttpStatus getHttpStatus() {
        return httpStatus == null ? defaultHttpStatus() : httpStatus;
    }

    /**
     * This method will be used in {@link #getMessage()} if {@link #message} is null.
     */
    protected abstract String defaultMessage();

    /**
     * Returns error message.
     * <p>
     * This method is used by {@link ControllerAdvisor} as a response body.
     * If {@link #message} is null, {@link #defaultMessage()} will be used.
     * See {@link ControllerAdvisor} for more information.
     * </p>
     */
    public String getMessage() {
        return message == null ? defaultMessage() : message;
    }
}
