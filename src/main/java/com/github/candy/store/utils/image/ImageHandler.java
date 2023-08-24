package com.github.candy.store.utils.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageHandler {
    public String uploadImage(MultipartFile image) {
        if (image == null) {
            return null;
        }
        return "https://i.imgur.com/";
    }
}
