package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AtivityPoupancas extends AppCompatActivity {

    Button btnVoltarPoupancas;
    TextView txtTotalAtual, txtTotalDivida, txtTotalFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativity_poupancas);

        btnVoltarPoupancas=findViewById(R.id.btnVoltarPoupancas);
        txtTotalAtual=findViewById(R.id.txtTotalAtual);
        txtTotalDivida=findViewById(R.id.txtTotalDivida);
        txtTotalFinal=findViewById(R.id.txtTotalFinal);
        //TODO Popular cada textview
    }

    private void voltar() {
        finish();
    }
}