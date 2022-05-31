package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTotalMonetario extends AppCompatActivity {

    Button btnInserir, btnRetirar, btnVoltar;
    EditText txtValorInserir;
    String valorInserido;
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

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserirValorMonetario();
            }
        });

        btnRetirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retirarValorMonetario();
            }
        });

    }


    private void inserirValorMonetario() {


        //inserir valor monetário no programa. Enviar tamebm para a base de dados o valor inserido
        valorInserido = txtValorInserir.getText().toString();
        Double valorexistente = Double.parseDouble(txtTotalInserido.getText().toString());
        if (valorInserido != null) {

            Double temp = Double.parseDouble(valorInserido) + valorexistente;
            txtTotalInserido.setText(temp.toString());
            //TODO enviar o valor para a base de dados que por sua ves faz update á singleton
        } else {
            Toast.makeText(getApplicationContext(), "Insira um valor a acrescentar", Toast.LENGTH_SHORT).show();
        }

    }

    private void retirarValorMonetario() {
        //TODO Retirar valor monetário no programa. Enviar tamebm para a base de dados o valor retirado e atualizar na textView
        valorInserido = txtValorInserir.getText().toString();
        Double valorexistente = Double.parseDouble(txtTotalInserido.getText().toString());
        if (valorInserido != null) {

            Double temp = valorexistente - Double.parseDouble(valorInserido);
            if (temp < 0) {
                Toast.makeText(getApplicationContext(), "Valor não pode dar negativo. O valor total foi colocado a 0.", Toast.LENGTH_SHORT).show();
                txtTotalInserido.setText("0");
            } else {
                txtTotalInserido.setText(temp.toString());
            }

            //TODO enviar o valor para a base de dados que por sua ves faz update á singleton
        } else {
            Toast.makeText(getApplicationContext(), "Insira um valor a retirar", Toast.LENGTH_SHORT).show();
        }
    }

    private void voltar() {
        finish();
    }

}