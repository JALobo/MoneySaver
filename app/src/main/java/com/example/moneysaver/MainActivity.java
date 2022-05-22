package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botaoLogin, botaoRegistar ;

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

    }

    private void goActivityLogin(){
        Intent login = new Intent(this, ActivityLogin.class);
        startActivity(login);
    }

    private void goActivityRegister(){
        Intent register = new Intent(this, ActivityRegisto.class);
        startActivity(register);
    }

}