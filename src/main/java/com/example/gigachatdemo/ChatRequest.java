package com.example.gigachatdemo;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(@NotBlank String question) {
}
