package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonRestController {
private final PersonService personService;

@Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/people")
    public ResponseEntity<List<Person>> getPeople() {
        List<Person> personList = personService.getPeople();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/api/person/{id}")
    public ResponseEntity<?> getPerson(@PathVariable int id) {
        Person person = personService.getPerson(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PERSON NOT FOUND");
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping("/api/person/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        if (person.getFirstName() == null  || person.getFamilyName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(person);
        }
        personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PutMapping("/api/person/edit/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
        try {
            Person existingPerson = personService.getPerson(id);
            if (existingPerson == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("PERSON NOT FOUND");
            }
            updatedPerson.setId(id);
            personService.setPerson(updatedPerson);
            Person updated = personService.getPerson(id);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating person: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/person/delete/{id}")
    public ResponseEntity<?> removePerson(@PathVariable int id) {
        Person person = personService.getPerson(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("PERSON NOT FOUND");
        }
        personService.removePerson(id);
        return ResponseEntity.ok(Map.of( "PERSON DELETED",  person));
    }
}