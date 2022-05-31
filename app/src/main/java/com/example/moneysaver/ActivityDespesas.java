package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityDespesas extends AppCompatActivity {

    //TODO https://abhiandroid.com/ui/listview
    Button btnVoltarEditDespesa, btnGerirDespesas;
    TextView txtTotalInserido, txtTotalDespesas;
    ListView despesaList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        btnVoltarEditDespesa=findViewById(R.id.btnVoltarEditDespesa);
        btnGerirDespesas=findViewById(R.id.btnGerirDespesas);

        //TODO Inserir metodos para popular as TExtViews
        txtTotalDespesas=findViewById(R.id.txtTotalDespesas);
        txtTotalInserido=findViewById(R.id.txtTotalInserido);

        //TODO Inserir metodos para popular a lista
        despesaList.findViewById(R.id.despesaList);

        btnVoltarEditDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltar();
            }
        });

        btnGerirDespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goActivityGerirDespesas();
            }
        });
    }
    private void goActivityGerirDespesas() {
        Intent gerirDespesas = new Intent(this, ActivityGerirDespesas.class);
        startActivity(gerirDespesas);
    }

    private void voltar() {
        finish();
    }

    private void addListViewItems(/*TODO por os items que vai popular*/){

    }
}