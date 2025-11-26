package com.example.app;

public class Person {
    private String firstName;
    private String familyName;
    private Integer id;

    public Person() { }

    public Person(String firstName, String familyName, Integer id) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
