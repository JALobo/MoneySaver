package com.example.moneysaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Users {
    private String email;
    private String name;
    private String username;
    private String password;
    private double moneyToSave;
    private ArrayList<Expenses> expenses;

    public Users(String email, String name, String username, String password, double moneyToSave, ArrayList<Expenses> expenses) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.moneyToSave = moneyToSave;
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

    public double getMoneyToSave() {
        return moneyToSave;
    }

    public void setMoneyToSave(double moneyToSave) {
        this.moneyToSave = moneyToSave;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expenses> expenses) {
        this.expenses = expenses;
    }

    //TODO adicionar metodods que permitam retirar informação do user
}
