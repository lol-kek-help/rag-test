# Простейший Spring Boot demo для GigaChat

Это минимальное приложение на Java + Spring Boot:
- открываете страницу,
- вводите вопрос,
- получаете ответ от GigaChat.

## 1) Что нужно

- Java 17+
- Maven 3.9+
- Ваш `Authorization Key` (base64 строка)

## 2) Настройка

Перед запуском задайте переменные окружения:

```bash
export GIGACHAT_AUTH_KEY='ВАШ_AUTHORIZATION_KEY'
export GIGACHAT_SCOPE='GIGACHAT_API_PERS'
export GIGACHAT_MODEL='GigaChat'
export GIGACHAT_BASE_URL='https://gigachat.devices.sberbank.ru'
```

> Если используете другой scope/model, просто поменяйте значения.

## 3) Запуск

```bash
mvn spring-boot:run
```

После старта откройте:

- http://localhost:8080

## 4) API

Можно вызывать и напрямую:

```bash
curl -X POST 'http://localhost:8080/api/chat' \
  -H 'Content-Type: application/json' \
  -d '{"question":"Что такое Spring Boot?"}'
```

Пример ответа:

```json
{"answer":"..."}
```

## 5) Как это работает

1. Приложение получает access token через `POST /api/v2/oauth`.
2. С этим токеном отправляет вопрос в `POST /api/v1/chat/completions`.
3. Возвращает первый ответ модели.
