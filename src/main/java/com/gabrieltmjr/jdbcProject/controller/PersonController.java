package com.gabrieltmjr.jdbcProject.controller;
import com.gabrieltmjr.jdbcProject.model.Person;

import java.sql.*;
import java.util.Vector;

public class PersonController {

    Connection connection;
    Vector<Person> people;

    public PersonController(Vector<Person> people) {
        try {
            connectToDatabase();
            this.people = people;
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
    public void checkAllData() throws SQLException {
        Statement selectAll = connection.createStatement();
        ResultSet all = selectAll.executeQuery("SELECT * FROM Person");
        while (all.next()) {
            int numColumns = all.getMetaData().getColumnCount();
            for(int i = 1; i <= numColumns; i++) {
                System.out.println("COLUMN"+i+"="+all.getObject(i));
            }
        }
    }
    //Insert into database
    public void insertPerson(Person person, Vector<Person> people) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Person VALUES(?,?,?)");
        statement.setInt(1,person.getIdNumber());
        statement.setString(2,person.getName());
        statement.setByte(3,person.getAge());
        statement.executeUpdate();
    }
    //Update to database
    //Delete from database


    public Connection getConnection() {
        return connection;
    }
}
