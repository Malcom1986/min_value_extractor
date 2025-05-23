[![Java CI](https://github.com/Malcom1986/min_value_extractor/actions/workflows/main.yml/badge.svg)](https://github.com/Malcom1986/min_value_extractor/actions/workflows/main.yml)

# Minimum value extractor

Это веб-приложение на основе Spring Boot, которое позволяет находить N-ное минимальное значение из списка целых чисел, хранящихся в файле формата Excel (XLSX).

Приложение использует алгоритм *QuickSelect* для эффективного поиска N-ного минимального значения

## Системные требования

- Docker
- make

Для запуска в режиме разработки:

- Java 21

## Запуск приложения

Запуск приложения в Docker контейнере

```bash
make start
```

Запуск в режиме разработки (будет работать автоматический перезапуск сервера при изменении файлов)

```bash
make start-dev
```

Запуск тестов

```bash
make check
```

После запуска интерактивная документация swagger-ui будет доступна по [ссылке](http://localhost:8080/swagger-ui/index.html)

## Примеры запросов

Для поиска N-ного минимального элемента в Exel файле нужно отправить *POST* запрос на адрес */api/minimum* с телом:

```json
{
    "filePath": "app/assets/numbers.xlsx",
    "index": 1
}
```

Пример POST запроса для получения N-ного минимального элемента

```bash
http localhost:8080/api/minimum filePath=/app/assets/numbers.xlsx index=1
```

Пример ответа:

```bash
{
    "minValue": -20
}
```
