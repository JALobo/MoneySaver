package com.example.moneysaver;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Expenses implements Serializable {
    private String nameExpense;
    private String desExpense;
    private String valExpense;
    private String userId;

    // getter method for our id
    public String getId() {
        return id;
    }

    // setter method for our id
    public void setId(String id) {
        this.id = id;
    }

    // we are using exclude because
    // we are not saving our id
    @Exclude
    private String id;


    /*public Expenses(String nameExpense, String desExpense, String valExpense) {
        this.nameExpense = nameExpense;
        this.desExpense = desExpense;
        this.valExpense = valExpense;
    }*/

    public Expenses(String nameExpense, String desExpense, String valExpense, String userId) {
        this.nameExpense = nameExpense;
        this.desExpense = desExpense;
        this.valExpense = valExpense;
        this.userId = userId;
    }

    //Construtor vazio just because
    public Expenses() {
    }

    public String getNameExpense() {
        return nameExpense;
    }

    public void setNameExpense(String nameExpense) {
        this.nameExpense = nameExpense;
    }

    public String getDesExpense() {
        return desExpense;
    }

    public void setDesExpense(String desExpense) {
        this.desExpense = desExpense;
    }

    public String getValExpense() {
        return valExpense;
    }

    public void setValExpense(String valExpense) {
        this.valExpense = valExpense;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
