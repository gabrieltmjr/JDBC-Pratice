package com.gabrieltmjr.jdbcProject;

import com.gabrieltmjr.jdbcProject.viewer.PersonViewer;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        PersonViewer personViewer = new PersonViewer();
        try {
            personViewer.viewByEqualName("Peter");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }
}
