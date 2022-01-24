package com.gabrieltmjr.jdbcProject;

import com.gabrieltmjr.jdbcProject.menu.PersonMenu;

public class Application {
    public static void main(String[] args) {
        PersonMenu menu = new PersonMenu();
        menu.getMenu();
    }
}
