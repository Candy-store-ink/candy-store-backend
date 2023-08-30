package com.github.candy.store.exception;

import com.github.candy.store.modules.image.Image;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    private static ResponseEntity<ErrorResponse> makeErrorResponse(HttpStatus ex, String message) {
        return ResponseEntity
                .status(ex)
                .body(new ErrorResponse(ex, message));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(NotValidImageException.class)
    public ResponseEntity<ErrorResponse> handleNotValidImageException(NotValidImageException ex) {
        return makeErrorResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<ErrorResponse> handleFileSizeLimitExceededException(FileSizeLimitExceededException ex) {
        String message = String.format(
                "Image size is too big. Max size: %s MB.",
                Image.MAX_IMAGE_SIZE);
        return makeErrorResponse(HttpStatus.BAD_REQUEST, message);
    }
}
