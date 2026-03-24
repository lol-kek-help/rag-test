package com.example.gigachatdemo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GigaChatClient {

    private final RestClient restClient;
    private final GigaChatProperties properties;

    public GigaChatClient(GigaChatProperties properties, RestClient.Builder restClientBuilder) {
        this.properties = properties;
        this.restClient = restClientBuilder
                .baseUrl(properties.baseUrl())
                .build();
    }

    public String ask(String userQuestion) {
        String token = fetchAccessToken();

        Map<String, Object> requestBody = Map.of(
                "model", properties.model(),
                "messages", List.of(Map.of("role", "user", "content", userQuestion))
        );

        Map<String, Object> response = restClient.post()
                .uri("/api/v1/chat/completions")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(Map.class);

        if (response == null || response.get("choices") == null) {
            return "Пустой ответ от GigaChat";
        }

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices.isEmpty()) {
            return "Пустой ответ от GigaChat";
        }

        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return message != null ? String.valueOf(message.get("content")) : "Пустой ответ от GigaChat";
    }

    private String fetchAccessToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("scope", properties.scope());

        Map<String, Object> response = restClient.post()
                .uri("/api/v2/oauth")
                .header("Authorization", "Basic " + resolveAuthKey())
                .header("RqUID", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(Map.class);

        if (response == null || response.get("access_token") == null) {
            throw new IllegalStateException("Не удалось получить access_token от GigaChat");
        }

        return String.valueOf(response.get("access_token"));
    }

    private String resolveAuthKey() {
        if (notBlank(properties.authKey())) {
            return properties.authKey().trim();
        }

        if (notBlank(properties.clientId()) && notBlank(properties.clientSecret())) {
            String pair = properties.clientId().trim() + ":" + properties.clientSecret().trim();
            return Base64.getEncoder().encodeToString(pair.getBytes(StandardCharsets.UTF_8));
        }

        throw new IllegalStateException("Укажите gigachat.auth-key или пару gigachat.client-id + gigachat.client-secret");
    }

    private static boolean notBlank(String value) {
        return value != null && !value.isBlank();
    }
}
