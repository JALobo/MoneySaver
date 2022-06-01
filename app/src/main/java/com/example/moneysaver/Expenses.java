package com.example.moneysaver;

public class Expenses {
    private String nameExpense;
    private String desExpense;
    private String valExpense;


    public Expenses(String nameExpense, String desExpense, String valExpense) {
        this.nameExpense = nameExpense;
        this.desExpense = desExpense;
        this.valExpense = valExpense;
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
    //TODO adicionar metodos para retirar informações das despesas (Valor despesa)
}
