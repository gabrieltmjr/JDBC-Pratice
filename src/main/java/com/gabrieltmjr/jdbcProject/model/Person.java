package com.gabrieltmjr.jdbcProject.model;

import java.util.Objects;

public class Person {
    private int idNumber;
    private String name;
    private byte age;

    public Person(int idNumber, String name, byte age) {
        this.idNumber = idNumber;
        this.name = name;
        this.age = age;
    }

    public int getIdNumber() { return idNumber; }

    public String getName() { return name; }

    public byte getAge() { return age; }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idNumber == person.idNumber && age == person.age && Objects.equals(name, person.name);
    }
}
