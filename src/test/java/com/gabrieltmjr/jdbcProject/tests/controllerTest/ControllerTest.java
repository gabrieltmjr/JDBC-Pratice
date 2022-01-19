package com.gabrieltmjr.jdbcProject.tests.controllerTest;

import com.gabrieltmjr.jdbcProject.controller.PersonController;
import com.gabrieltmjr.jdbcProject.model.Person;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Vector;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {

    PersonController personController;

    @BeforeEach
    public void testConnectionToDatabase() {
        personController = new PersonController();
        Assertions.assertNotEquals(null,personController.getConnection());
    }

    @Test
    public void insertPersonTest() {
        boolean passed = true;
        Person person = new Person(2,"Therry",(byte)26);
        try {
            personController.insertPerson(person);
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
            personController.readAllPeople();
        } catch (SQLException s) {
            passed = false;
            System.out.println(s.getMessage());
        }

        Assertions.assertEquals(3,personController.getPeople().size());
        Assertions.assertEquals(0,personController.getPeople().elementAt(0).getIdNumber());
        Assertions.assertEquals(1,personController.getPeople().elementAt(1).getIdNumber());
        Assertions.assertEquals(2, personController.getPeople().elementAt(2).getIdNumber());

        Assertions.assertTrue(passed);
    }

    @Test
    public void updatePersonTest() {
        boolean passed = true;
        try {
            personController.readAllPeople();
            Person person = personController.getPeople().get(1);
            person.setName("Chris");
            person.setAge((byte) 15);
            personController.updatePerson(person);
            personController.readAllPeople();
            Assertions.assertEquals("Chris",personController.getPeople().get(1).getName());
            Assertions.assertEquals(15,personController.getPeople().get(1).getAge());
        } catch (SQLException s) {
            passed = false;
            System.out.println(s.getMessage());
        }

        Assertions.assertTrue(passed);
    }

    @Test
    public void deletePersonTest() {

    }
}
