package com.github.candy.store.config.props.security;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for JWT token.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class JwtProps {

    /**
     * JWT secret for secure encoding.
     */
    @NotBlank
    private String secret;

    /**
     * JWT expiration time in milliseconds. Default is 31 days.
     */
    @Positive
    private long expiration = 2_678_400_000L;

    /**
     * JWT refresh time in milliseconds. Default is 62 days.
     */
    @Positive
    private long refreshExpiration = 5_443_200_000L;
}
