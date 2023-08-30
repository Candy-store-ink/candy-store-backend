package com.github.candy.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.candy.store.exception.ErrorResponse;
import com.github.candy.store.exception.NotValidImageException;
import com.github.candy.store.modules.image.Image;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MultiPartFileFilter extends OncePerRequestFilter {

    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Part image = request.getPart("image");
            if (image != null) {
                validateAllowedContentType(image);
                validateFileName(image);
                validateSize(image);
            }
        } catch (FileSizeLimitExceededException e) {
            log.error(e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
            return;
        } catch (NotValidImageException e) {
            log.error(e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), new ErrorResponse(e.getHttpStatus(), e.getMessage()));
            return;
        } catch (IOException | IllegalStateException | ServletException ignored) {
        }
        doFilter(request, response, filterChain);
    }

    private void validateSize(Part image) {
        if (image.getSize() > Image.MAX_IMAGE_SIZE * Image.MB) {
            String message = String.format(
                    "Image size is too big.  Max size: %s MB.",
                    Image.MAX_IMAGE_SIZE);
            throw new NotValidImageException(message);
        }
    }

    private void validateAllowedContentType(Part image) {
        if (ALLOWED_CONTENT_TYPES.stream().noneMatch(type -> type.equals(image.getContentType()))) {
            String message = String.format(
                    "Image type %s is not allowed. Allowed types: %s.",
                    image.getContentType(),
                    ALLOWED_CONTENT_TYPES);
            throw new NotValidImageException(message);
        }
    }

    private void validateFileName(Part image) {
        if (image.getSubmittedFileName() == null || image.getSubmittedFileName().isEmpty()) {
            String message = "Not valid image name.";
            throw new NotValidImageException(message);
        }
    }
}
