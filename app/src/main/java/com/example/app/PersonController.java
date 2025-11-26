package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/add")
    public String add(Model model) {
        Person newPerson = new Person();
        model.addAttribute("person", newPerson);
        return "edit";
    }

    @GetMapping("/people")
    public String people(Model model) {
        model.addAttribute("personList", personService.getPeople());
        return "people";
    }

    @GetMapping("/person/{id}")
    public String show(@PathVariable int id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "person";
    }

    @GetMapping("/person/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Person person = personService.getPerson(id);
        model.addAttribute("person", person);
        return "edit";
    }

    @PostMapping("/person/edit/save")
    public String save(Person person) {
        if (person.getId() == null) {
            personService.addPerson(person);
        } else {
            personService.setPerson(person);
        }
        return "redirect:/people";
    }

    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable int id) {
        personService.removePerson(id);
        personService.setPeopleIds();
        return "redirect:/people";
    }
}
