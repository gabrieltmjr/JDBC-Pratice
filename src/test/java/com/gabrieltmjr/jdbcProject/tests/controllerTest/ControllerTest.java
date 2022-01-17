package com.gabrieltmjr.jdbcProject.tests.controllerTest;

import com.gabrieltmjr.jdbcProject.controller.PersonController;
import com.gabrieltmjr.jdbcProject.model.Person;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Vector;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {

    PersonController personController;
    Vector<Person> people;

    @BeforeEach
    public void testConnectionToDatabase() {
        personController = new PersonController(people);
        Assertions.assertNotEquals(null,personController.getConnection());
    }

    @Test
    public void insertPersonTest() {
        boolean passed = true;
        Person person = new Person(2,"Therry",(byte)26);
        try {
            personController.insertPerson(person,people);
        } catch (SQLException s) {
            passed = false;
            System.out.println(s.getMessage());
        }

        Assertions.assertTrue(passed);
    }

    @Test
    public void checkAllTest(){
        boolean passed = true;
        try {
            personController.checkAllData();
        } catch (SQLException s) {
            passed = false;
            System.out.println(s.getMessage());
        }

        Assertions.assertTrue(passed);
    }
}
