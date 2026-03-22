# Простейший Spring Boot demo для GigaChat

Это минимальное приложение на Java + Spring Boot:
- открываете страницу,
- вводите вопрос,
- получаете ответ от GigaChat.

## 1) Что нужно

- Java 17+
- Maven 3.9+
- Ваш `Authorization Key` (base64 строка)

Проверьте окружение перед запуском:

```bash
java -version
mvn -version
```

## 2) Настройка

Перед запуском задайте переменные окружения.

### Linux / macOS

```bash
export GIGACHAT_AUTH_KEY='ВАШ_AUTHORIZATION_KEY'
export GIGACHAT_SCOPE='GIGACHAT_API_PERS'
export GIGACHAT_MODEL='GigaChat'
export GIGACHAT_BASE_URL='https://gigachat.devices.sberbank.ru'
```

### Windows PowerShell

```powershell
$env:GIGACHAT_AUTH_KEY='ВАШ_AUTHORIZATION_KEY'
$env:GIGACHAT_SCOPE='GIGACHAT_API_PERS'
$env:GIGACHAT_MODEL='GigaChat'
$env:GIGACHAT_BASE_URL='https://gigachat.devices.sberbank.ru'
```

> Если используете другой scope/model, просто поменяйте значения.

## 3) Запуск

### Linux / macOS

```bash
mvn spring-boot:run
```

### Windows PowerShell

```powershell
mvn.cmd spring-boot:run
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

## 6) Если на Windows ошибка `-classpath requires class path specification`

Обычно причина не в этом проекте, а в переменных окружения Java/Maven (например, некорректный `MAVEN_OPTS` / `JAVA_TOOL_OPTIONS`).

1. Проверьте переменные:

```powershell
gci env:MAVEN_OPTS
gci env:JAVA_TOOL_OPTIONS
gci env:JAVA_OPTS
```

2. Если там есть `-classpath` или странные JVM-флаги — очистите их и запустите снова:

```powershell
Remove-Item env:MAVEN_OPTS -ErrorAction SilentlyContinue
Remove-Item env:JAVA_TOOL_OPTIONS -ErrorAction SilentlyContinue
Remove-Item env:JAVA_OPTS -ErrorAction SilentlyContinue
mvn.cmd spring-boot:run
```

3. Если не помогло, соберите jar и запустите напрямую:

```powershell
mvn.cmd clean package
java -jar .\target\gigachat-demo-0.0.1-SNAPSHOT.jar
```
