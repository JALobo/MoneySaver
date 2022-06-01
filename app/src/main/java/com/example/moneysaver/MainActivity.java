package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button botaoLogin, botaoRegistar ;
    BroadcastReceiver broadcastReceiver;
    Users user;//Variavel de teste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarUserTeste();//Apagar este método quando deixar de ser necessário

        botaoLogin = findViewById(R.id.btLogin);
        botaoRegistar =  findViewById(R.id.btRegistar);
        //Botão para ir para a actividade de login
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent do login
                goActivityLogin();
            }
        });
        //Botão para ir para a actividade de registo
        botaoRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent do registar
                goActivityRegister();
            }
        });


        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity_MainActivity")) {
                    unregisterReceiver(broadcastReceiver);
                    finish();
                    // DO WHATEVER YOU WANT.

                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_activity"));

    }

    private void goActivityLogin(){
        Intent login = new Intent(this, ActivityLogin.class);
        startActivity(login);

    }


    private void goActivityRegister(){
        Intent register = new Intent(this, ActivityRegisto.class);
        startActivity(register);
    }

    //para apagar quando o projeto tiver completo
    public void criarUserTeste(){

        Expenses expenses1 = new Expenses("Elden Ring","Wolf husbando >_>",60.0);
        Expenses expenses2 = new Expenses("Chocolate","I am fucking depressed",1.10);
        Expenses expenses3 = new Expenses("BD","If you know, you know",110.0);
        ArrayList<Expenses> expenses = new ArrayList<>();
        expenses.add(expenses1);
        expenses.add(expenses2);
        expenses.add(expenses3);
        user = new Users("joao.andre.lobo@gmail.com","João Lobo", "Cysper","12345",20, expenses);
        SingletonCarregarDados.setUsers(user);
    }
}