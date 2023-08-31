package com.github.candy.store.modules.image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "image")
@NoArgsConstructor
public class Image {

    /**
     * Max image size in megabytes
     */
    public static final int MAX_IMAGE_SIZE = 5;
    public static final int MB = 1024 * 1024;

    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    public Image(String path, String mimeType) {
        this.path = path;
        this.mimeType = mimeType;
    }
}
