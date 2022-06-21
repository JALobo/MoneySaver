package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityMenuPrincipal extends AppCompatActivity {

    Button totalMonetario, despesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        totalMonetario = findViewById(R.id. btnTotalMonetario);
        despesas = findViewById(R.id.btnDespesas);

        totalMonetario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent do total monetário
                goActivityTotalMonetario();
            }
        });

        despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent das despesas
                goActivityDespesas();
            }
        });

    }


    private void goActivityTotalMonetario() {
        Intent totalMonetario = new Intent(this, ActivityTotalMonetario.class);
        startActivity(totalMonetario);
    }

    private void goActivityDespesas() {
        Intent despesas = new Intent(this, ActivityDespesas.class);
        startActivity(despesas);
    }


}