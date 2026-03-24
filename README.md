# Простейший Spring Boot demo для GigaChat

Это минимальное приложение на Java + Spring Boot:
- открываете страницу,
- вводите вопрос,
- получаете ответ от GigaChat.

## Быстрый старт

В `src/main/resources/application.properties` уже заполнены ваши данные:
- `Authorization Key`
- `Client ID`
- `Client Secret`

Поэтому можно запускать сразу.

## Запуск

### Linux / macOS

```bash
./mvnw spring-boot:run
```

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

После старта откройте:

- http://localhost:8080

## Если в PowerShell ошибка `-classpath requires class path specification`

Скорее всего проблема в переменных JVM/Maven в вашей среде, а не в коде.

Проверьте, что вы вводите **ровно одну команду** без символов `\n` в конце:

```powershell
.\mvnw.cmd spring-boot:run
```

Дальше очистите возможные конфликтные переменные и повторите запуск:

```powershell
Remove-Item env:MAVEN_OPTS -ErrorAction SilentlyContinue
Remove-Item env:JAVA_TOOL_OPTIONS -ErrorAction SilentlyContinue
Remove-Item env:JAVA_OPTS -ErrorAction SilentlyContinue
.\mvnw.cmd spring-boot:run
```

Если всё равно ошибка, запустите через jar:

```powershell
.\mvnw.cmd clean package
java -jar .\target\gigachat-demo-0.0.1-SNAPSHOT.jar
```

## API

```bash
curl -X POST 'http://localhost:8080/api/chat' \
  -H 'Content-Type: application/json' \
  -d '{"question":"Что такое Spring Boot?"}'
```

Ответ:

```json
{"answer":"..."}
```
