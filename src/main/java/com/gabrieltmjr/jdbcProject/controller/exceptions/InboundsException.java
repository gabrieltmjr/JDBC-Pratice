package com.gabrieltmjr.jdbcProject.controller.exceptions;

public class InboundsException extends Throwable {
    private int min;
    private int max;

    public InboundsException(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String getMessage() {
        return "Error! Please input a number between "+min+" and "+max;
    }
}
