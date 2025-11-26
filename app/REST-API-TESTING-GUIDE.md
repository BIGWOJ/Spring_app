# Spring Boot REST API - Przykłady testowania z curl

## Uruchomienie aplikacji
```bash
cd "S:\Przedmioty\VII semestr\AI2\Spring_app\app"
.\mvnw.cmd spring-boot:run
```

## 1. GET /api/people - Pobranie wszystkich osób

### ✅ POPRAWNY:
```bash
curl -X GET http://localhost:8080/api/people
```

**Oczekiwany Status:** 200 OK
**Oczekiwana odpowiedź:**
```json
[
  {"firstName": "John", "familyName": "Doe"},
  {"firstName": "Jane", "familyName": "Smith"},
  {"firstName": "Alice", "familyName": "Johnson"}
]
```

---

## 2. GET /api/people/{id} - Pobranie konkretnej osoby

### ✅ POPRAWNY:
```bash
curl -X GET http://localhost:8080/api/people/0
```

**Oczekiwany Status:** 200 OK
**Oczekiwana odpowiedź:**
```json
{"firstName": "John", "familyName": "Doe"}
```

### ❌ NIEPOPRAWNY (404):
```bash
curl -X GET http://localhost:8080/api/people/999
```

**Oczekiwany Status:** 404 Not Found
**Oczekiwana odpowiedź:** `Person not found`

---

## 3. POST /api/people - Dodanie nowej osoby

### ✅ POPRAWNY:
```bash
curl -X POST http://localhost:53894/api/person/add \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Maria",
    "familyName": "Kowalska"
  }'
```

**Oczekiwany Status:** 201 Created
**Oczekiwana odpowiedź:**
```json
{"firstName": "Maria", "familyName": "Kowalska"}
```

### ❌ NIEPOPRAWNY (brak wymaganych pól):
```bash
curl -X POST http://localhost:8080/api/people \
  -H "Content-Type: application/json" \
  -d '{
    "invalidField": "test"
  }'
```

**Oczekiwany Status:** 400 Bad Request (lub błąd walidacji)

---

## 4. PUT /api/people/{id} - Aktualizacja osoby

### ✅ POPRAWNY:
```bash
curl -X PUT http://localhost:8080/api/people/0 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Johnathan",
    "familyName": "Doe"
  }'
```

**Oczekiwany Status:** 200 OK
**Oczekiwana odpowiedź:**
```json
{"firstName": "Johnathan", "familyName": "Doe"}
```

### ❌ NIEPOPRAWNY (404):
```bash
curl -X PUT http://localhost:8080/api/people/999 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Test",
    "familyName": "User"
  }'
```

**Oczekiwany Status:** 404 Not Found
**Oczekiwana odpowiedź:** `Person not found`

---

## 5. DELETE /api/people/{id} - Usunięcie osoby

### ✅ POPRAWNY:
```bash
curl -X DELETE http://localhost:8080/api/people/0
```

**Oczekiwany Status:** 204 No Content
**Oczekiwana odpowiedź:** Brak treści

### ❌ NIEPOPRAWNY (404):
```bash
curl -X DELETE http://localhost:8080/api/people/999
```

**Oczekiwany Status:** 404 Not Found
**Oczekiwana odpowiedź:** `Person not found`

---

## Testowanie sekwencyjne:

1. Sprawdź początkowe dane: `GET /api/people`
2. Dodaj nową osobę: `POST /api/people`
3. Sprawdź czy została dodana: `GET /api/people`
4. Aktualizuj pierwszą osobę: `PUT /api/people/0`
5. Sprawdź zmianę: `GET /api/people/0`
6. Usuń osobę: `DELETE /api/people/1`
7. Sprawdź finalny stan: `GET /api/people`

---

## Testowanie w PowerShell:
```powershell
.\test-rest-api.ps1
```

## Testowanie w Postman:
1. Utwórz nową kolekcję "Spring App REST API"
2. Dodaj requesty z powyższymi parametrami
3. Ustaw environment variable: baseUrl = http://localhost:8080
4. Uruchom testy sekwencyjnie