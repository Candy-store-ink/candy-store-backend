package com.github.candy.store.controllers;

import com.github.candy.store.utils.image.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("image")
    public void getImages(@RequestParam String id, HttpServletResponse response) {
        this.imageService.findById(id).ifPresent(image -> {
            try (OutputStream out = response.getOutputStream()) {
                File file = new File(image.getPath());
                byte[] data = Files.readAllBytes(file.toPath());
                response.setContentType(image.getMimeType());
                out.write(data);
            } catch (Exception e) {
                log.error("Error while getting image", e);
            }
        });
    }
}
