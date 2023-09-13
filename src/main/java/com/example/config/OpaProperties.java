package com.example.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;

@ConfigurationProperties(prefix = "opa")
@Data
@Getter
@Configuration
public class OpaProperties {
    @Nonnull
    private String endpoint = "http://localhost:8181";

    public String getEndpoint() {
        return endpoint;
    }
}
