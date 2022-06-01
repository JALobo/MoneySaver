package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityGerirDespesas extends AppCompatActivity {

    Button btnAdicionarDespesa, btnRemoverDespesa, btnVoldarGerirDespesas;

    //TODO Substituir isto depois pelo o que que vem da base de dados
    //Para ter o ListView a funcionar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerir_despesas);
        btnAdicionarDespesa = findViewById(R.id.btnAdicionarDespesa);
        btnRemoverDespesa = findViewById(R.id.btnRemoverDespesa);
        btnVoldarGerirDespesas = findViewById(R.id.btnVoldarGerirDespesas);

        btnVoldarGerirDespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnAdicionarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO chamar o fragment de adicionar a despesa
            }
        });

        btnRemoverDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO chamar o fragment de remoção de despesa
            }
        });


    }

    private void voltar() {
        finish();
    }
}