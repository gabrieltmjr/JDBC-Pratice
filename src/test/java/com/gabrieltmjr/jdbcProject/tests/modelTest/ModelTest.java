package com.gabrieltmjr.jdbcProject.tests.modelTest;

import com.gabrieltmjr.jdbcProject.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {

    Person person;

    @BeforeEach
    public void helper() {
        byte age = 18;
        String name = "Manuel";
        int idNumber = 0;
        person = new Person(idNumber,name,age);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(18, person.getAge());
        Assertions.assertEquals("Manuel",person.getName());
        Assertions.assertEquals(0,person.getIdNumber());
    }

    @Test
    public void equalsTest() {
        Assertions.assertEquals(person, new Person(person.getIdNumber(), person.getName(), person.getAge()));
    }
}
