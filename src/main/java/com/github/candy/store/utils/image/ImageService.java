package com.github.candy.store.utils.image;

import com.github.candy.store.config.props.AppProps;
import com.github.candy.store.modules.image.Image;
import com.github.candy.store.modules.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final AppProps props;

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile image) {
        if (image == null) {
            log.warn("Warning while uploading image: image is null. Image will be skipped");
            return null;
        }
        String imageLocation = props.getImage().getLocation();
        String fileName = image.getOriginalFilename();
        String mimeType = image.getContentType();
        String path = imageLocation + File.separator + UUID.randomUUID() + "." + fileName;
        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            log.error("Error while uploading image", e);
            return null;
        }
        String id = imageRepository.save(new Image(path, mimeType)).getId();
        log.info("Image with id={} uploaded successfully", id);
        return props.getServerUrl() + props.getImage().getPath() + id;

    }

    public Optional<Image> findById(String id) {
        return imageRepository.findById(id);
    }
}
