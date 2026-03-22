package com.example.gigachatdemo;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "gigachat")
public record GigaChatProperties(
        @NotBlank String authKey,
        @NotBlank String scope,
        @NotBlank String model,
        @NotBlank String baseUrl
) {
}
