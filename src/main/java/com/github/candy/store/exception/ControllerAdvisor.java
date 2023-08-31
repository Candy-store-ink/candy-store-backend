package com.github.candy.store.exception;

import com.github.candy.store.modules.image.Image;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is used to handle exceptions thrown by services and controllers.
 * <p>
 * All methods in this class should return {@link ResponseEntity} with {@link ErrorResponse} as a body type.
 * </p>
 */
@ControllerAdvice
public class ControllerAdvisor {

    /**
     * Handles all exceptions that extend {@link BaseHttpException}.
     * <p>
     * This method will return a response o
     *
     * </p>
     */
    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(BaseHttpException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleFileSizeLimitExceededException(FileSizeLimitExceededException ignored) {
        String message = String.format("Image size is too big. Max size: %s MB.", Image.MAX_IMAGE_SIZE);
        return makeErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * This method makes a response with specified HTTP status code and message.
     * @param ex HTTP status code
     * @param message error message
     * @return response with specified HTTP status code and message
     * @see ResponseEntity
     * @see ErrorResponse
     */
    private static ResponseEntity<ErrorResponse> makeErrorResponse(HttpStatus ex, String message) {
        return ResponseEntity.status(ex).body(new ErrorResponse(ex, message));
    }
}
