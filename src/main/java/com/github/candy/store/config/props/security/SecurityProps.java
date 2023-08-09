package com.github.candy.store.config.props.security;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Security properties.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SecurityProps {

    /**
     * Properties for JWT token.
     */
    @Valid
    @NestedConfigurationProperty
    private final JwtProps jwt = new JwtProps();

}
