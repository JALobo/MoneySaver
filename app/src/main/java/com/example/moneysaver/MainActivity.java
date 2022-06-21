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
    //Users user;//Variavel de teste
    Expenses exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void goActivityLogin() {
        Intent login = new Intent(this, ActivityLogin.class);
        startActivity(login);
    }

    private void goActivityRegister() {
        Intent registo = new Intent(this, ActivityRegisto.class);
        startActivity(registo);
    }


}