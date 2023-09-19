package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "opa")
@Data
public class OpaProperties {
    private String endpoint = "http://localhost:8181";

    public String getEndpoint() {
        return endpoint;
    }
}