package com.gabrieltmjr.jdbcProject.viewer;

import com.gabrieltmjr.jdbcProject.controller.image.ManageImage;

import javax.swing.*;
import java.sql.*;

public class PersonViewer {

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    public PersonViewer() {
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

    public void viewAllPeople() throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person");
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewPerson(int idNumber) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE idNumber = ?");
        statement.setInt(1,idNumber);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewPersonImage(int idNumber) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE idNumber = ?");
        statement.setInt(1,idNumber);
        resultSet = statement.executeQuery();
        byte[] blobImage = resultSet.getBytes(4);
        ImageIcon profilePicture = new ManageImage(blobImage).blobToImageIcon();
        new ImageViewer(profilePicture);
    }

    public void viewByAgeLessThan(int age) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE age < ?");
        statement.setInt(1,age);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewByAgeMoreThan(int age) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE age > ?");
        statement.setInt(1,age);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewByEqualAge(int age) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE age == ?");
        statement.setInt(1,age);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewByEqualName(String name) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM Person WHERE name == ?");
        statement.setString(1,name);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

    public void viewPeopleOrderByAge(byte order) throws SQLException {
        String ord = "ASC";
        if(order == 1) ord = "DESC";
        statement = connection.prepareStatement("SELECT * FROM Person ORDER BY ?");
        statement.setString(1,ord);
        resultSet = statement.executeQuery();
        System.out.println("idNumber |   name   |  age  |");
        System.out.println("=============================");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1)+"    "+resultSet.getObject(2)+"    "+resultSet.getObject(3));
        }
    }

}
