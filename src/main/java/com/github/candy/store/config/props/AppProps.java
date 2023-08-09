package com.github.candy.store.config.props;

import com.github.candy.store.config.props.security.SecurityProps;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Application properties.
 * <p>
 * This class is used to store all custom application properties.
 * </p>
 */
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AppProps {

    /**
     * Security properties.
     */
    @NestedConfigurationProperty
    @Valid
    private final SecurityProps security = new SecurityProps();
}
