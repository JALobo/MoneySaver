package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ActivityDespesas extends AppCompatActivity {

    Button btnVoltarEditDespesa, btnAdicionarDespesa;
    TextView txtTotalInserido, txtTotalDespesas;
    RecyclerView recyclerView;
    ArrayList<Expenses> expenses;
    //TODO Ligar a database
    HelperAdapter helperAdapter;
    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        expenses = new ArrayList<>();
        Expenses expenses1 = new Expenses("Elden Ring", "Wolf husbando >_>", "60.0");
        Expenses expenses2 = new Expenses("Chocolate", "I am fucking depressed", "1.10");
        Expenses expenses3 = new Expenses("BD", "If you know, you know", "110.0");
        expenses.add(expenses1);
        expenses.add(expenses2);
        expenses.add(expenses3);

        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();


        btnVoltarEditDespesa = findViewById(R.id.btnVoltarEditDespesa);
        btnAdicionarDespesa = findViewById(R.id.btnAdicionarDespesa);

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

        btnAdicionarDespesa.setOnClickListener(new View.OnClickListener() {
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

    private void addDataToFirestore(Expenses expenses){

    }
}