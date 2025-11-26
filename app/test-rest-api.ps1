# Test Script for Spring Boot REST API
# Uruchom ten skrypt w PowerShell po uruchomieniu aplikacji

Write-Host "=== TESTOWANIE ENDPOINTÓW REST ===" -ForegroundColor Green
Write-Host ""

$baseUrl = "http://localhost:8080/api/people"

# Test 1: GET All People (POPRAWNY)
Write-Host "1. GET /api/people (POPRAWNY)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri $baseUrl -Method GET
    Write-Host "Status: 200 OK" -ForegroundColor Green
    Write-Host "Odpowiedź:" ($response | ConvertTo-Json -Depth 2)
} catch {
    Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 2: GET Person by ID (POPRAWNY)
Write-Host "2. GET /api/people/0 (POPRAWNY)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/0" -Method GET
    Write-Host "Status: 200 OK" -ForegroundColor Green
    Write-Host "Odpowiedź:" ($response | ConvertTo-Json -Depth 2)
} catch {
    Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 3: GET Person by ID (NIEPOPRAWNY - 404)
Write-Host "3. GET /api/people/999 (NIEPOPRAWNY - 404)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/999" -Method GET
    Write-Host "Status: 200 OK (Nieoczekiwane)" -ForegroundColor Red
    Write-Host "Odpowiedź:" ($response | ConvertTo-Json -Depth 2)
} catch {
    if ($_.Exception.Response.StatusCode -eq 404) {
        Write-Host "Status: 404 Not Found (Oczekiwane)" -ForegroundColor Green
    } else {
        Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
    }
}
Write-Host ""

# Test 4: POST New Person (POPRAWNY)
Write-Host "4. POST /api/people (POPRAWNY)" -ForegroundColor Yellow
$newPerson = @{
    firstName = "Maria"
    familyName = "Kowalska"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri $baseUrl -Method POST -Body $newPerson -ContentType "application/json"
    Write-Host "Status: 201 Created" -ForegroundColor Green
    Write-Host "Odpowiedź:" ($response | ConvertTo-Json -Depth 2)
} catch {
    Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 5: PUT Update Person (POPRAWNY)
Write-Host "5. PUT /api/people/0 (POPRAWNY)" -ForegroundColor Yellow
$updatePerson = @{
    firstName = "Johnathan"
    familyName = "Doe"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/0" -Method PUT -Body $updatePerson -ContentType "application/json"
    Write-Host "Status: 200 OK" -ForegroundColor Green
    Write-Host "Odpowiedź:" ($response | ConvertTo-Json -Depth 2)
} catch {
    Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 6: PUT Update Person (NIEPOPRAWNY - 404)
Write-Host "6. PUT /api/people/999 (NIEPOPRAWNY - 404)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/999" -Method PUT -Body $updatePerson -ContentType "application/json"
    Write-Host "Status: 200 OK (Nieoczekiwane)" -ForegroundColor Red
} catch {
    if ($_.Exception.Response.StatusCode -eq 404) {
        Write-Host "Status: 404 Not Found (Oczekiwane)" -ForegroundColor Green
    } else {
        Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
    }
}
Write-Host ""

# Test 7: DELETE Person (POPRAWNY)
Write-Host "7. DELETE /api/people/1 (POPRAWNY)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/1" -Method DELETE
    Write-Host "Status: 204 No Content" -ForegroundColor Green
} catch {
    if ($_.Exception.Response.StatusCode -eq 204) {
        Write-Host "Status: 204 No Content (Oczekiwane)" -ForegroundColor Green
    } else {
        Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
    }
}
Write-Host ""

# Test 8: DELETE Person (NIEPOPRAWNY - 404)
Write-Host "8. DELETE /api/people/999 (NIEPOPRAWNY - 404)" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$baseUrl/999" -Method DELETE
    Write-Host "Status: 200 OK (Nieoczekiwane)" -ForegroundColor Red
} catch {
    if ($_.Exception.Response.StatusCode -eq 404) {
        Write-Host "Status: 404 Not Found (Oczekiwane)" -ForegroundColor Green
    } else {
        Write-Host "Błąd: $($_.Exception.Message)" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "=== TESTOWANIE ZAKOŃCZONE ===" -ForegroundColor Green