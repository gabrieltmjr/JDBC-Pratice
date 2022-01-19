package com.gabrieltmjr.jdbcProject.controller;
import com.gabrieltmjr.jdbcProject.model.Person;

import java.sql.*;
import java.util.Vector;

public class PersonController {

    Connection connection;
    Vector<Person> people;

    public PersonController() {
        people = new Vector<>();
        try {
            connectToDatabase();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }


    }
    //Connect to Database
    private void connectToDatabase() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/database/example";
        connection = DriverManager.getConnection(url);
    }
    //Read from database
    public void checkAllPeople() throws SQLException {
        int idNumber; String name; byte age;
        Statement selectAll = connection.createStatement();
        ResultSet all = selectAll.executeQuery("SELECT * FROM Person");
        while (all.next()) {
            idNumber = (Integer) all.getObject(1);
            name = (String) all.getObject(2);
            age = ((Integer) all.getObject(3)).byteValue();
            System.out.println(idNumber+" "+name+" "+age);
        }
    }

    public void readAllPeople() throws SQLException {
        people = new Vector<>();
        Person person; String name;
        int idNumber; byte age;
        Statement selectAll = connection.createStatement();
        ResultSet all = selectAll.executeQuery("SELECT * FROM Person");
        while (all.next()) {
            idNumber = (Integer) all.getObject(1);
            name = (String) all.getObject(2);
            age = ((Integer) all.getObject(3)).byteValue();
            person = new Person(idNumber,name,age);
            people.add(person);
        }
    }
    //Insert into database
    public void insertPerson(Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Person VALUES(?,?,?)");
        statement.setInt(1,person.getIdNumber());
        statement.setString(2,person.getName());
        statement.setByte(3,person.getAge());
        statement.executeUpdate();
    }
    //Update to database
    public void updatePerson(Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE Person SET name = ?, age = ? WHERE idNumber = ?");
        statement.setString(1,person.getName());
        statement.setByte(2,person.getAge());
        statement.setInt(3, person.getIdNumber());
        statement.executeUpdate();
    }

    //Delete from database
    public void deletePerson(Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Person WHERE idNumber = ?");
        statement.setInt(1,person.getIdNumber());
        statement.executeUpdate();
    }

    public Vector<Person> getPeople() {
        return people;
    }

    public Connection getConnection() {
        return connection;
    }
}
