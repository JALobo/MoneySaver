package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTotalMonetario extends AppCompatActivity {

    Button btnInserir, btnRetirar, btnVoltar;
    EditText txtValorInserir;
    TextView txtTotalInserido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_monetario);

        btnInserir = findViewById(R.id.btnInserirTM);
        btnRetirar = findViewById(R.id.btnRetirarTM);
        btnVoltar = findViewById(R.id.btnVoltar);
        txtValorInserir = findViewById(R.id.txtValorInserir);
        txtTotalInserido = findViewById(R.id.txtTotalInserido);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

    }


    private void inserirValorMonetario() {

        //inserir valor monetário no programa. Enviar tamebm para a base de dados o valor inserido

    }

    private void retirarValorMonetario() {
        //Retirar valor monetário no programa. Enviar tamebm para a base de dados o valor retirado

    }

    private void voltar() {
        finish();
    }

}