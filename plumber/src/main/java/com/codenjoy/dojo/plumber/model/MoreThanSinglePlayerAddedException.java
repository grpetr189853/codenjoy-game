package com.codenjoy.dojo.plumber.model;

public class MoreThanSinglePlayerAddedException extends RuntimeException {
    public MoreThanSinglePlayerAddedException() {
        super("Plumber game can have only single player!");
    }
}
