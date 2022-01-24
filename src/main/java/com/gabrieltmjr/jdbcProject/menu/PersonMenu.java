package com.gabrieltmjr.jdbcProject.menu;

import com.gabrieltmjr.jdbcProject.controller.PersonController;
import com.gabrieltmjr.jdbcProject.controller.exceptions.InboundsException;
import com.gabrieltmjr.jdbcProject.model.Person;
import com.gabrieltmjr.jdbcProject.viewer.PersonViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Stack;

public class PersonMenu {

    private Stack<Byte> menu;
    private final byte MAIN_MENU = 0;
    private final byte CHECK_ALL_PEOPLE = 1;
    private final byte ADD_PERSON_MENU = 2;
    private final byte REMOVE_PERSON_MENU = 3;
    private final byte UPDATE_DATA_MENU = 4;
    private final byte EXIT = 5;
    private byte menuOption;
    private BufferedReader br;

    public PersonMenu() {
        menu = new Stack<>();
        menu.push((byte)0);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void getMenu() {
        switch (menu.peek()){
            case MAIN_MENU: mainMenu();
            case CHECK_ALL_PEOPLE: checkAllPeople();
            case ADD_PERSON_MENU: addPersonMenu();
            case REMOVE_PERSON_MENU: removePersonMenu();
            case UPDATE_DATA_MENU: updatePersonMenu();
            case EXIT: System.exit(0);
        }
    }

    private void mainMenu() {
        System.out.println("Welcome!");
        System.out.println("1 - Check all people");
        System.out.println("2 - Add a Person");
        System.out.println("3 - Remove a Person");
        System.out.println("4 - Update data from a person");
        System.out.println("5 - Exit");
        menuOption = readByte((byte) 1,(byte) 5);
        menu.push(menuOption);
        getMenu();
    }

    private void addPersonMenu() {
        String name;
        int idNumber; byte age;
        name = readString();
        idNumber = readInt(0,100000);
        age = readByte((byte) 0,(byte) 127);
        try {
            new PersonController().insertPerson(new Person(idNumber,name,age));
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        menu.pop();
        getMenu();
    }

    private void removePersonMenu() {
        int idNumber;
        PersonController pc = new PersonController();
        try {
            pc.readAllPeople();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        System.out.println("Insert the persons id");
        idNumber = readInt(1,pc.getPeople().lastElement().getIdNumber());
        for (Person person: pc.getPeople()) {
            if(person.getIdNumber() == idNumber) {
                try {
                    pc.deletePerson(person);
                    break;
                }catch (SQLException s) {
                    System.out.println(s.getMessage());
                }
            }
        }

        menu.pop();
        getMenu();
    }

    private void updatePersonMenu() {
        int idNumber; boolean exists = false;
        byte option;
        PersonController pc = new PersonController();
        try {
            pc.readAllPeople();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        System.out.println("Insert the persons id");
        idNumber = readInt(1,pc.getPeople().lastElement().getIdNumber());
        for (Person person: pc.getPeople()) {
            if(person.getIdNumber() == idNumber) {
                exists = true;
                try {
                    System.out.println("0 - Name and age, 1 - Name, 2 - Age");
                    option = readByte((byte) 0,(byte) 2);
                    if(option == 0) {
                        System.out.println("Insert the new name");
                        String name = readString();
                        System.out.println("Insert the new age");
                        byte age = readByte((byte) 0,(byte) 127);
                        person.setName(name);
                        person.setAge(age);
                    } else if(option == 1) {
                        System.out.println("Insert the new name");
                        String name = readString();
                        person.setName(name);
                    } else if(option == 2) {
                        System.out.println("Insert the new age");
                        byte age = readByte((byte) 0,(byte) 127);
                        person.setAge(age);
                    }
                    pc.updatePerson(person);
                    break;
                }catch (SQLException s) {
                    System.out.println(s.getMessage());
                }
            }
        }

        if(!exists) System.out.println("Person does not exist");

        menu.pop();
        getMenu();
    }


    private void checkAllPeople() {
        try {
            new PersonViewer().viewAllPeople();
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        menu.pop();
        getMenu();
    }

    private byte readByte(byte min, byte max) {
        byte option = -1;
        System.out.println("Input a number between "+min+" and "+max);
        boolean pass = false;
        while (!pass) {
            try {
                option = Byte.parseByte(br.readLine());
                if(option < min || option > max) throw new InboundsException(min,max);
                pass = true;
            } catch (NumberFormatException n) {
                System.out.println("Error! Please introduce a number");
            } catch (InboundsException in) {
                System.out.println(in.getMessage());
            } catch (IOException i) {
                System.out.println(i.getMessage());
            }

        }
        return option;
    }

    private int readInt(int min, int max) {
        int option = -1;
        System.out.println("Input a number between "+min+" and "+max);
        boolean pass = false;
        while (!pass) {
            try {
                option = Byte.parseByte(br.readLine());
                if(option < min || option > max) throw new InboundsException(min,max);
                pass = true;
            } catch (NumberFormatException n) {
                System.out.println("Error! Please introduce a number");
            } catch (InboundsException in) {
                System.out.println(in.getMessage());
            } catch (IOException i) {
                System.out.println(i.getMessage());
            }

        }
        return option;
    }

    private String readString() {
        System.out.println("Input the person name");
        boolean pass = false;
        String name = "";
        while (!pass) {
            try {
                name = br.readLine();
                pass = true;
            } catch (IOException i) {
                System.out.println(i.getMessage());
            }
        }
        return name;
    }


}
