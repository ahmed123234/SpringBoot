package com.example.databasejdbc;

import java.util.Date;

public class User {
    // here the fields names should be as same as table column names
    private String username;
    private String email;
    private String password;
    private Date creation_date;

    public String getUsername() {
        return username;
    }
//    public User(){
//        super();
//    }
    public User()
    {

    }

    public User(String username, String email, String password, Date creation_date) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.creation_date = creation_date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
