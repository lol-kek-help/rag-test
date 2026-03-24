package com.example.gigachatdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gigachat")
public record GigaChatProperties(
        String authKey,
        String clientId,
        String clientSecret,
        String scope,
        String model,
        String baseUrl
) {
}
