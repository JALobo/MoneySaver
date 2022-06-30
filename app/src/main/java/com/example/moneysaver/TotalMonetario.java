package com.example.moneysaver;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class TotalMonetario implements Serializable {
    private String userId;
    private String totalMonetario;
    private String titulo;

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



    public TotalMonetario(String userId, String totalMonetario, String titulo) {
        this.userId = userId;
        this.totalMonetario = totalMonetario;
        this.titulo = titulo;
    }

    //aparentemente é necessário um vazio
    public TotalMonetario(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalMonetario() {
        return totalMonetario;
    }

    public void setTotalMonetario(String totalMonetario) {
        this.totalMonetario = totalMonetario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
