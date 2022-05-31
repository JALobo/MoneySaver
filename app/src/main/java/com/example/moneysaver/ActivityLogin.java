package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {


    Button btnPasswordEsquecida, btnEntrar;
    EditText txtUsername, txtPassword;
    String admin, password;

    // to get Context
    Context context;
    // toast time duration, can also set manual value
    int duration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;

        //Variaveis de developer
        admin = "admin";
        password = "admin";

        //Butões
        btnPasswordEsquecida = findViewById(R.id.btnPasswordEsquecida);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        //Botão passwoard Esquecida
        btnPasswordEsquecida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Leva para o intent da passwaord esquecida (Ver se vai dar para fazer)
            }
        });

        //Botão entrar
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Leva para o main menu on sucesseful login
                verifieLogin();
            }
        });
    }

    private void verifieLogin() {

        //Caso o username esteja errado

        boolean isAllowed=true;

        Users user = SingletonCarregarDados.getUsers();
        //TODO Pedir a lista de users da DB
        if (!(txtUsername.getText().toString().equals(user.getUsername()) )) {
            Toast.makeText(context, "Utilizador errado", duration).show();
            isAllowed = false;
        }
        //caso a password esteja Errada
        if (!(txtPassword.getText().toString().equals(user.getPassword()))) {
            Toast.makeText(context, "Password errada", duration).show();
            isAllowed = false;
        }

        if(isAllowed){
            //TODO enviar o user para o singleton
        goActivityMenuprincipal();
        }
    }

    private void goActivityMenuprincipal() {
        Intent menuPrincipal = new Intent(this, ActivityMenuPrincipal.class);
        startActivity(menuPrincipal);
        ActivityLogin.this.finish();
        Intent intent = new Intent("finish_activity_MainActivity");
        sendBroadcast(intent);

    }

   /* public void finishLogin(View v) {
        ActivityLogin.this.finish();
    }*/
}