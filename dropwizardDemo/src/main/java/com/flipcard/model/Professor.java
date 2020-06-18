package com.flipcard.model;

public class Professor extends User{

    public Professor(){

    }

    public Professor(int id, String username, String password, String name, String email) {
        super(id, username, password, name, email, 2);
    }

    public Professor(String username, int id, String name, String email) {
        super(username, id, name, email);
    }

}
