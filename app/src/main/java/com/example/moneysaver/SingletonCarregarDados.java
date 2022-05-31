package com.example.moneysaver;

public class SingletonCarregarDados {
    //TODO criar uma singleton para faer update aos dados.
    private static Users users;

    public static Users getUsers() {
        return users;
    }

    public static void setUsers(Users users) {
        SingletonCarregarDados.users = users;
        //TODO Adicionar na base de dados
    }

    //TODO Criar um metodo update()
    public static void updateUsers(){
        //TODO ligar este método á firebase
    }
}
