package com.example.app;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList;
    private Integer nextPersonId;

    @PostConstruct
    public void init() {
        personList = new ArrayList<>();
        personList.add(new Person("Test_Imie", "Test_Nazwisko", 1 ));
        personList.add(new Person("Test_Imie_2", "Test_Nazwisko_2", 2));
        nextPersonId = 3;
    }

    public List<Person> getPeople() {
        return personList;
    }

    public Person getPerson(int index) {
        return personList.stream().filter(p -> p.getId().equals(index)).findFirst().orElse(null);
    }

    public void addPerson(Person person) {
        person.setId(nextPersonId);
        personList.add(person);
        nextPersonId++;
    }

    public void setPerson(Person newPerson) {
        Person updatingPerson = getPerson(newPerson.getId());
        if (updatingPerson != null) {
            updatingPerson.setFirstName(newPerson.getFirstName());
            updatingPerson.setFamilyName(newPerson.getFamilyName());
        }
    }

    public void removePerson(int index) {
        personList.removeIf(p -> p.getId().equals(index));
    }

    public void setPeopleIds() {
        int id = 1;
        for (Person person : personList) {
            person.setId(id);
            id++;
        }
        nextPersonId = id;
    }
}