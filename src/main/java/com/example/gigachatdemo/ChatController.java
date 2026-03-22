package com.example.gigachatdemo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final GigaChatClient gigaChatClient;

    public ChatController(GigaChatClient gigaChatClient) {
        this.gigaChatClient = gigaChatClient;
    }

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        String answer = gigaChatClient.ask(request.question());
        return new ChatResponse(answer);
    }
}
