package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ActivityDespesas extends AppCompatActivity {

    Button btnVoltarEditDespesa, btnGerirDespesas;
    TextView txtTotalInserido, txtTotalDespesas;
    RecyclerView recyclerView;
    ArrayList<Expenses> expenses = SingletonCarregarDados.getUsers().getExpenses();
//TODO Ligar a database
HelperAdapter helperAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);


        btnVoltarEditDespesa = findViewById(R.id.btnVoltarEditDespesa);
        btnGerirDespesas = findViewById(R.id.btnGerirDespesas);

        //TODO Inserir metodos para popular as TExtViews
        txtTotalDespesas = findViewById(R.id.txtTotalDespesas);
        txtTotalInserido = findViewById(R.id.txtTotalInserido);


        //----------Tentativa de por o Recicler view  a funcionar-------------

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helperAdapter = new HelperAdapter(expenses);
        recyclerView.setAdapter(helperAdapter);






        //-----------------------

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

    private void addListViewItems(/*TODO por os items que vai popular*/) {

    }
}