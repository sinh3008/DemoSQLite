package com.example.sqlitedatabase.entity;

public class Information {
    private int ID;
    private String Name;
    private String Number;
    private String Email;

    public Information(String name, String number, String email) {
        Name = name;
        Number = number;
        Email = email;
    }

    public Information() {
    }

    public Information(int ID, String name, String number, String email) {
        this.ID = ID;
        Name = name;
        Number = number;
        Email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}