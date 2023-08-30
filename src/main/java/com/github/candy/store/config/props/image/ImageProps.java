package com.github.candy.store.config.props.image;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageProps {

    /**
     * Image location on the server.
     */
    @NotBlank
    private String location;

    /**
     * Path to the get image.
     */
    @NotBlank
    private String path;

}
