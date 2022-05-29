package com.example.moneysaver;

import java.util.ArrayList;
import java.util.Map;

public class Users {
    private String email;
    private String name;
    private String username;
    private String password;
    private Map expenses;

    public Users(String email, String name, String username, String password, Map expenses) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.expenses = expenses;
    }

    //Construtor vazio just because
    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map getExpenses() {
        return expenses;
    }

    public void setExpenses(Map expenses) {
        this.expenses = expenses;
    }
}
